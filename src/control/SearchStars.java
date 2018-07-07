package control;

import dao.DataSource;
import dao.PerimetroDao;
import dao.StellaDao;
import entity.PuntoPerimetro;
import entity.Stella;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import static java.lang.Math.pow;

@SuppressWarnings("Duplicates")
public class SearchStars {

    /**
     * Da qui parte il controllore del REQ-FN-10. Questo metodo trova la quantità in percentuale dei tipi di stelle
     * all'interno di una regione
     *
     * @param b
     * @param h
     * @param c
     * @return Double[]
     */
    public Double[] searchStarsRect(Double b, Double h, Double[] c) {

        StellaDao sD = new StellaDao();
        PerimetroDao perD = new PerimetroDao();
        Vector<PuntoPerimetro> vecPer;
        SearchObjectsInFilamento search = new SearchObjectsInFilamento();
        Double[] coorSt = new Double[2];
        DataSource dS = new DataSource();
        Connection con = dS.getConnection();
        Double[] tipiStelle = new Double[8];
        vecPer = perD.getAllPerimetri(con);

        double presIn = 0;
        double prosIn = 0;
        double unbIn = 0;
        double formIn = 0;

        double presEs = 0;
        double prosEs = 0;
        double unbEs = 0;
        double formEs = 0;

        Double[] k = new Double[2];             //   pD ______________  pA
        Double[] pA = new Double[2];            //     |              |
        Double[] pB = new Double[2];            //     |    c .- - - -| k
        Double[] pC = new Double[2];            //     |              |
        Double[] pD = new Double[2];            //     |______________|
                                                //    pC                pB


        Double d = pow(((b * b) + (h * h)), (0.5));
        Double dM = d / 2;
        Double hM = h / 2;
        Double i = pow(((dM * dM) - (hM * hM)), (0.5));
        Double cX = c[0];
        Double cY = c[1];
        Double kX = cX + i;
        Double kY = cY;
        pA[0] = kX;
        pA[1] = kY + hM;
        Double pAX = pA[0];
        Double pAY = pA[1];
        pB[0] = kX;
        pB[1] = kY - hM;
        Double pBX = pB[0];
        Double pBY = pB[1];
        pC[0] = pBX - b;
        pC[1] = pBY;
        Double pCX = pC[0];
        Double pCY = pC[1];
        pD[0] = pCX;
        pD[1] = pAY;
        Double pDX = pD[0];
        Double pDY = pD[1];
        System.out.println(d + " - " + dM + " - " + hM + " - " +
                i + " - " + cX + " - " + cY
                + " - " + kX + " - " + kY);
        System.out.println(pAX + " - " + pAY);
        System.out.println(pBX + " - " + pBY);
        System.out.println(pCX + " - " + pCY);
        System.out.println(pDX + " - " + pDY);
        System.out.println();

        int num = 0;

        try{
            Vector vec = sD.getAllStars(con);
            int l = vec.size();
            while (num < l) {

                Stella stella = (Stella) vec.get(num);
                Double pLat = stella.getLatitudine();
                Double pLon = stella.getLongitudine();
                String t = stella.getTipo();
                System.out.println(pLat);
                System.out.println(pLon);
                //System.out.println(t);
                /*boolean bool = pLon <= pAX && pLon <= pBX && pLon >= pDX && pLon >= pCX
                        && pLat <= pAY && pLat >= pBY && pLat <= pDY && pLat >= pCY;
                System.out.println(bool);*/

                if (pLon <= pAX && pLon <= pBX && pLon >= pDX && pLon >= pCX
                        && pLat <= pAY && pLat >= pBY && pLat <= pDY && pLat >= pCY){

                    if(search.stellaInFilVecPer(pLon, pLat, vecPer, con)) {

                        switch (t) {
                            case "PRESTELLAR":
                                presIn++;
                                //System.out.println(pres);
                                break;
                            case "PROTOSTELLAR":
                                prosIn++;
                                //System.out.println(pros);

                                break;
                            case "UNBOUND":
                                unbIn++;
                                //System.out.println(unb);

                                break;
                            case "FORMATA":
                                formIn++;
                                //System.out.println(form);

                                break;
                            default:
                                System.out.println("...SEARCHING...");

                                break;
                        }

                    }else {
                        switch (t) {
                            case "PRESTELLAR":
                                presEs++;
                                //System.out.println(pres);
                                break;
                            case "PROTOSTELLAR":
                                prosEs++;
                                //System.out.println(pros);

                                break;
                            case "UNBOUND":
                                unbEs++;
                                //System.out.println(unb);

                                break;
                            case "FORMATA":
                                formEs++;
                                //System.out.println(form);

                                break;
                            default:
                                System.out.println("...SEARCHING...");

                                break;
                        }
                    }
                }
                num ++;
            }

            double totIn = presIn + prosIn + unbIn + formIn;
            presIn = ((presIn) / totIn) * 100;
            prosIn = ((prosIn) / totIn) * 100;
            unbIn = ((unbIn) / totIn) * 100;
            formIn = ((formIn) / totIn) * 100;

            double totEs = presEs + prosEs + unbEs + formEs;
            presEs = ((presEs) / totEs) * 100;
            prosEs = ((prosEs) / totEs) * 100;
            unbEs = ((unbEs) / totEs) * 100;
            formEs = ((formEs) / totEs) * 100;

            tipiStelle[0] = presIn;
            tipiStelle[1] = prosIn;
            tipiStelle[2] = unbIn;
            tipiStelle[3] = formIn;

            tipiStelle[4] = presEs;
            tipiStelle[5] = prosEs;
            tipiStelle[6] = unbEs;
            tipiStelle[7] = formEs;


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println(num);
        return tipiStelle;
    }

    public static void main(String[] args) {
        SearchStars sS = new SearchStars();
        Double[] tipiStelle;
        Double[] c = new Double[2];
        c[0] = Double.valueOf(150);
        c[1] = Double.valueOf(2);

        tipiStelle = sS.searchStarsRect(200.0, 10.5, c);
        Double[] t = tipiStelle;
        System.out.println(t[0] + "\n" + t[1] + "\n" + t[2] + "\n" + t[3]
                + "\n" + t[4] + "\n" + t[5] + "\n" + t[6] + "\n" + t[7]);

    }
}
