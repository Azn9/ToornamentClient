import static org.junit.Assert.*;

import ch.wisv.toornament.ToornamentClient;
import ch.wisv.toornament.concepts.Tournaments;
import ch.wisv.toornament.concepts.TournamentsV2;
import ch.wisv.toornament.model.Tournament;
import ch.wisv.toornament.model.TournamentDetails;
import ch.wisv.toornament.model.enums.MatchFormat;
import ch.wisv.toornament.model.enums.ParticipantType;
import ch.wisv.toornament.model.request.TournamentRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

public class TournamentsTests {
    private ToornamentClient client;
    private ToornamentClient client1;
    //@Mock
    Tournaments caller = Mockito.mock(Tournaments.class);
    private HashMap<String,String> params;
    private HashMap<String,String> headers;

    private TournamentRequest tournamentRequest = new TournamentRequest();
    private TournamentDetails tournamentDetails = new TournamentDetails();
    @Before
    public void Setup() throws IOException {
        client = new ToornamentClient(System.getenv("KEY"),System.getenv("CLIENT"),System.getenv("SECRET"));
        client.authorize();
        client1 = new ToornamentClient(System.getenv("KEY"),System.getenv("CLIENT"),System.getenv("SECRET"));
        client1.authorize();

        headers = new HashMap<>();
        params = new HashMap<>();
        params.put("disciplines","overwatch");

        tournamentDetails.setParticipantType(ParticipantType.TEAM);
        tournamentDetails.setName("OWL Season 1 TEST");
        tournamentDetails.setSize(144);
        tournamentDetails.setDiscipline("overwatch");

        tournamentRequest.setName("OWL Season 1 TEST");
        tournamentRequest.setDiscipline("overwatch");
        tournamentRequest.setOrganization("Blizzard Entertainment");
        tournamentRequest.setWebsite("http://www.overwatchleague.com");
        tournamentRequest.setMatchFormat(MatchFormat.BO3);
        tournamentRequest.setPrize("1 - $10,000 \n 2 - $5,000");
        tournamentRequest.setSize(144);
        tournamentRequest.setParticipantType(ParticipantType.TEAM);
        tournamentRequest.setTimezone("America/Los_Angeles");
        tournamentRequest.setCountry("US");
        tournamentRequest.setDateStart(LocalDate.parse("2018-09-05",DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        tournamentRequest.setDateEnd(LocalDate.parse("2019-04-05",DateTimeFormatter.ofPattern("yyyy-MM-dd")));


    }

    @Test
    public void getFeaturedTournamentsTest() {
            headers.put("range","tournaments=0-49");
            List<Tournament> details = client1.tournamentsV2().getFeaturedTournaments(params,headers);

            ArrayList<Tournament> list = new ArrayList<>(details);

                assertTrue(!list.isEmpty());
    }

    @Test
    public void getTournamentsWithParamsTest(){
        List<Tournament> details = client.tournaments().getTournamentsWithParams(params);
        assertTrue(!details.isEmpty());
    }

    @Test
    public void getTournamentTest(){

        TournamentDetails tournament = client.tournamentsV2().getTournament("1257784630743515136");
        assertEquals("overwatch",tournament.getDiscipline());
    }

    @Test
    public void getAllTournamentsTest(){
        List<Tournament> list = client.tournaments().getAllTournaments();
        assertTrue(!list.isEmpty());
    }

    @Test
    public void createTournamentTest(){
        System.out.println(client.tournaments().createTournament(tournamentRequest));

    }

    @Test
    public void deleteTournamentTest(){
       List<Tournament> list = client.tournaments().getMyTournaments();
        for(Tournament t : list)
       client.tournaments().deleteTournament(t.getId());

       assertTrue(client.tournaments().getMyTournaments().isEmpty());
    }

}
