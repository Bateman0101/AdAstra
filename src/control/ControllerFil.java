package control;

import dao.FilamentoPerNumEllLumDao;
import dao.DistanzaDaContornoDao;
import entity.Filamento;
import entity.Segmento;
import exceptions.NoEstremoException;
import exceptions.NoPerimetroException;
import java.sql.SQLException;
import java.util.List;

public class ControllerFil {

    /**
     * Wrapper per il metodo {@link dao.FilamentoPerNumEllLumDao#search(int, int)}
     * @param inf il numero minimo di Segmenti
     * @param sup il numero massimo di Segmenti
     * @return lista di Filamenti
     * @throws SQLException se si verifica qualcosa di inaspettato nel database
     */
    public List<Filamento> ricercaNumS(int inf, int sup) throws SQLException {
        if(inf > 0 && sup > inf+2) {
            List<Filamento> l;
            FilamentoPerNumEllLumDao dao = new FilamentoPerNumEllLumDao();
            l = dao.search(inf, sup);
            return l;
        }
        if(inf == 0 && sup > inf+2) {
            List<Filamento> l;
            FilamentoPerNumEllLumDao dao = new FilamentoPerNumEllLumDao();
            l = dao.searchAlt(sup);
            return l;
        } else {
            return null;
        }
    }

    /**
     * Wrapper per il metodo {@link dao.FilamentoPerNumEllLumDao#searchLum(double)}
     * @param lum valore percentuale di luminosità(lum deve essere maggiore di 0)
     * @return lista di Filamenti
     * @throws SQLException se si verifica qualcosa di inaspettato nel database
     */
    public List<Filamento> ricercaLum(double lum) throws SQLException{
        if (lum > 0) {
            double contrasto = 1 + (lum / 100);
            List<Filamento> l;
            FilamentoPerNumEllLumDao dao = new FilamentoPerNumEllLumDao();
            l = dao.searchLum(contrasto);
            return l;
        } else {
            return null;
        }
    }

    /**
     * Wrapper per il metodo {@link FilamentoPerNumEllLumDao#getTot()}
     * @return numero totale di filamenti presenti nel database
     * @throws SQLException se si verifica qualcosa di inaspettato nel database
     */
    public int getTot() throws SQLException {
        FilamentoPerNumEllLumDao dao = new FilamentoPerNumEllLumDao();
        int tot = dao.getTot();
        return tot;
    }

    /**
     * Wrapper per il metodo {@link dao.FilamentoPerNumEllLumDao#searchEll(Double, Double)}
     * @param inf estremo inferiore dell'intervallo di ricerca(inf deve essere maggiore di 1)
     * @param sup estremo superiore dell'intervallo di ricerca(sup deve essere minore di 10)
     * @return lista di Filamenti
     * @throws SQLException se si verifica qualcosa di inaspettato nel database
     */
    public List<Filamento> ricercaEll(Double inf, Double sup) throws SQLException{
        if(inf > 1 && sup < 10 && sup > inf) {
            FilamentoPerNumEllLumDao dao = new FilamentoPerNumEllLumDao();
            List<Filamento> l;
            l = dao.searchEll(inf, sup);
            return l;
        } else {
            return null;
        }
    }

    /**
     * Wrapper per il metodo {@link dao.DistanzaDaContornoDao#getDistance(int, int, String)}
     * @param idS ID del segmento
     * @param idF ID del filamento
     * @param sat nome del satellite
     * @return le distanze degli estremi del segmento dal contorno del filamento a cui appartiene
     * @throws SQLException se si verifica qualcosa di inaspettato nel database
     * @throws NoPerimetroException se il filamento è privo di punti di contorno
     * @throws NoEstremoException se il segmento è privo di punti
     */
    public double[] getDistance(int idS, int idF, String sat) throws SQLException, NoPerimetroException, NoEstremoException {
        DistanzaDaContornoDao dao = new DistanzaDaContornoDao();
        return dao.getDistance(idS, idF, sat);
    }

    /**
     * Wrapper per il metodo {@link dao.DistanzaDaContornoDao#search(int, String)}
     * @param fil ID del filamento
     * @param sat nome del satellite
     * @return lista di segmenti appartenenti al filamento dato
     * @throws SQLException se si verifica qualcosa di inaspettato nel database
     */
    public List<Segmento> searchBranch(int fil, String sat) throws SQLException{
        DistanzaDaContornoDao dao = new DistanzaDaContornoDao();
        return dao.search(fil, sat);
    }

}
