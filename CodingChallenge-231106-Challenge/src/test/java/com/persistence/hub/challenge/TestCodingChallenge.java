package com.persistence.hub.challenge;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import com.persistence.hub.challenge.query.TournamentValue;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@SuppressWarnings({"unchecked", "deprecation"})
public class TestCodingChallenge {

    Logger log = LogManager.getLogger(this.getClass().getName());


    private EntityManagerFactory emf;


    /**
     * Adjust the query so that it returns the result as TournamentValue objects.
     * Each tournament shall only be mapped to 1 TournamentValue object and
     * contain a full list of player names.
     */
    @Test
    public void testCodingChallenge1() {
        log.info("... testCodingChallenge1 ...");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();


        List<TournamentValue> tournamentValues = (List<TournamentValue>) em.unwrap(Session.class)
                .createQuery("""
                        SELECT t.id,
                        		t.name,
                        		p.firstName,
                        		p.lastName
                        FROM ChessTournament t
                        		LEFT JOIN t.players p""")
                .setResultListTransformer(list -> {
                    List<Object[]> l = list;
                    List<TournamentValue> tournamentValueList = new ArrayList<>();
                    log.info("Transform list");
                    Long lastId = null;
                    TournamentValue value = new TournamentValue();
                    for (Object[] e : l) {
                        if (e[0] != lastId) {
                            value = new TournamentValue();
                            value.setId((Long) e[0]);
                            value.setTournamentName((String) e[1]);
                            lastId = (Long) e[0];
                            value.getPlayerNames().add(e[2] + " " + e[3]);

                            tournamentValueList.add(value);
                        } else {
                            value.getPlayerNames().add(e[2] + " " + e[3]);
                        }
                    }

                    return tournamentValueList;
                }).getResultList();

        validateAndLogResult(tournamentValues);

        em.getTransaction().commit();
        em.close();
    }

    private void validateAndLogResult(List<TournamentValue> tournamentValues) {
        // validate results
        assertThat(tournamentValues.size()).isEqualTo(2);
        tournamentValues.forEach(t -> {
            switch (t.getId().intValue()) {
                case 1:
                    assertThat(t.getTournamentName()).isEqualTo("Fide Grand Swiss 2023");
                    assertThat(t.getPlayerNames().size()).isEqualTo(4);
                    break;
                case 2:
                    assertThat(t.getTournamentName()).isEqualTo("Titled Tuesday");
                    assertThat(t.getPlayerNames().size()).isEqualTo(2);
                    break;
                default:
                    fail();
            }
        });

        tournamentValues.forEach(t -> {
            log.info(t.getTournamentName() + " " + t.getPlayerNames());
        });
    }

    @Before
    public void init() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    }

    @After
    public void close() {
        emf.close();
    }
}
