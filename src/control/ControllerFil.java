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
    public List<Filamento> ricercaNumS(int inf, int sup) throws SQLException {
        if(inf >= 0 && sup > inf+2) {
            List<Filamento> l;
            FilamentoPerNumEllLumDao dao = new FilamentoPerNumEllLumDao();
            l = dao.search(inf, sup);
            return l;
        } else {
            return null;
        }
    }

    public List<Filamento> ricercaLum(float lum) throws SQLException{
        float contrasto = 1 + (lum/100);
        List<Filamento> l;
        FilamentoPerNumEllLumDao dao = new FilamentoPerNumEllLumDao();
        l = dao.searchLum(contrasto);
        return l;
    }

    public int getTot(){
        FilamentoPerNumEllLumDao dao = new FilamentoPerNumEllLumDao();
        int tot = dao.getTot();
        return tot;
    }

    public List<Filamento> ricercaEll(Float inf, Float sup) throws SQLException{
        FilamentoPerNumEllLumDao dao = new FilamentoPerNumEllLumDao();
        List<Filamento> l;
        l = dao.searchEll(inf, sup);
        return l;
    }
    public double[] getDistance(int idS, int idF, String sat) throws SQLException, NoPerimetroException, NoEstremoException {
        DistanzaDaContornoDao dao = new DistanzaDaContornoDao();
        return dao.getDistance(idS, idF, sat);
    }
    public List<Segmento> searchBranch(int fil, String sat) throws SQLException{
        DistanzaDaContornoDao dao = new DistanzaDaContornoDao();
        return dao.search(fil, sat);
    }

}
