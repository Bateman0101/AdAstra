package control;

import dao.RicercaNumSegmenti;
import dao.RicercaSegmenti;
import entity.Filamento;
import java.util.List;

public class ControllerFil {
    public List<Filamento> ricercaNumS(int inf, int sup) {
        if(inf >= 0 && sup >= inf+2) {
            List<Filamento> l;
            RicercaNumSegmenti dao = new RicercaNumSegmenti();
            l = dao.search(inf, sup);
            return l;
        } else {
            return null;
        }
    }

    public List<Filamento> ricercaLum(float lum){
        float contrasto = 1 + (lum/100);
        List<Filamento> l;
        RicercaNumSegmenti dao = new RicercaNumSegmenti();
        l = dao.searchLum(contrasto);
        return l;
    }

    public int getTot(){
        RicercaNumSegmenti dao = new RicercaNumSegmenti();
        int tot = dao.getTot();
        return tot;
    }
    public double[] getDistance(int idS, int idF, String sat) {
        RicercaSegmenti dao = new RicercaSegmenti();
        return dao.getDistance(idS, idF, sat);
    }


}
