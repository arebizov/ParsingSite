package ru.parsing.sevice;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.parsing.SourceData;
import ru.parsing.model.Offers;
import ru.parsing.model.PriceHisory;
import ru.parsing.model.Stores;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class Parsing {
    public void pars(List<SourceData> finalListAll) throws IOException {

//        ParsingExcel parsingExcel = new ParsingExcel();
        List<SourceData> list = finalListAll;
        List<String> uniqStores = new ArrayList<String>();


        Configuration configuration = new Configuration().addAnnotatedClass(Stores.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        String hql = "from Stores";
        Query query = session.createQuery(hql);
        List<Stores> listStores = query.list();

        for (SourceData listSt : list) {
            uniqStores.add(listSt.getStores());
        }

        try {
            List<String> uniq = uniqStores.stream().distinct().collect(Collectors.toList());
            for (int i = 0; i < uniq.size(); i++) {
                int j = 0;

                for (Stores st : listStores) {

                    if (uniq.get(i).equals(st.getStoreName())) {
                        j = j + 1;
                    }

                }
                if (j == 0) {
                    Stores stores = new Stores(uniq.get(i));
                    session.save(stores);
                }

            }
        } finally {
            session.getTransaction().commit();

        }
        SessionFactory sessionFactory2 = configuration.buildSessionFactory();
        Session session2 = sessionFactory2.getCurrentSession();
        session2.beginTransaction();


        List<UniqOffers> uniqueDataList;// = new ArrayList<>();


        try {
            Query query2 = session2.createQuery(hql);
            List<Stores> listStoresUpdate = query2.list();
            List<UniqOffers> listUniqOffers = new ArrayList<>();


            for (SourceData sd : list) {
                for (Stores st : listStoresUpdate) {
                    if (sd.getStores().equals(st.getStoreName())) {
                        sd.setStoreId(st.getId());
                        listUniqOffers.add(new UniqOffers(sd.getStoreId(), sd.getOfferName(), sd.getUnit(),sd.getCategory()));

                    }
                }
            }


            uniqueDataList = listUniqOffers.stream().distinct().collect(Collectors.toList());

        } finally {
            session2.getTransaction().commit();
            session2.close();
            sessionFactory2.close();
        }
        Configuration configuration1 = new Configuration().addAnnotatedClass(Offers.class);
        SessionFactory sessionFactory3 = configuration1.buildSessionFactory();
        Session session3 = sessionFactory3.getCurrentSession();
        session3.beginTransaction();
        String hqlOffers = "from Offers ";
        Query queryOffers = session3.createQuery(hqlOffers);
        List<Offers> listOffers = queryOffers.list();

        for (UniqOffers un : uniqueDataList) {
            int a = 0;
            for (Offers of : listOffers) {
                if (un.offerName.equals(of.getName()) && un.storeID == of.getStoreID()) {
                    a = a + 1;

                } else {

                }
            }
            if (a == 0) {
                Offers offers2 = new Offers(un.storeID, un.offerName, Math.abs(un.offerName.hashCode()) % 1000000000, un.unit, un.category);
                session3.save(offers2);
            }

        }
        session3.getTransaction().commit();
        session3.close();
        sessionFactory3.close();


        Configuration configuration4 = new Configuration().addAnnotatedClass(Offers.class);
        SessionFactory sessionFactory4 = configuration4.buildSessionFactory();
        Session session4 = sessionFactory4.getCurrentSession();
        session4.beginTransaction();

        Configuration configuration5 = new Configuration().addAnnotatedClass(PriceHisory.class);
        SessionFactory sessionFactory5 = configuration5.buildSessionFactory();
        Session session5 = sessionFactory5.getCurrentSession();
        session5.beginTransaction();

        List<PriceHisory> priceHisoryList = new ArrayList<>();


        try {
            List<PriceHisory> uniqHistoryList;
            Query queryOffersAll = session4.createQuery(hqlOffers);
            List<Offers> listOffersall = queryOffersAll.list();

            for (SourceData finalList : list) {
                for (Offers of : listOffersall) {
                    if (finalList.getOfferName().equals(of.getName()) && finalList.getStoreId() == of.getStoreID()) {
                        finalList.setOfferId(of.getOfferId());
                    }
                }
                int storeId = finalList.getStoreId();
                int offerId = finalList.getOfferId();
                double price = finalList.getPrice();
                double priceGold = finalList.getPrice();
                Date date = finalList.getDate();
                priceHisoryList.add(new PriceHisory(storeId,offerId ,price,priceGold,date));

            }
            uniqHistoryList = priceHisoryList.stream().distinct().collect(Collectors.toList());

            for(PriceHisory pr : uniqHistoryList ){

                String hqlDeletePrice = "delete PriceHisory where storeId =:id and date =:date and offerId =:offerId";
                Query queryDelete = session5.createQuery(hqlDeletePrice);
                queryDelete.setParameter("id", pr.getStoreId());
                queryDelete.setParameter("date", pr.getDate());
                queryDelete.setParameter("offerId", pr.getOfferId());
                queryDelete.executeUpdate();
            }

            for(PriceHisory pr : priceHisoryList ){
                PriceHisory priceHisory = new PriceHisory(pr.getStoreId(), pr.getOfferId(), pr.getPrice(), pr.getPriceGold(), pr.getDate());
                session5.save(priceHisory);
            }


        } finally {
            session5.getTransaction().commit();
            session5.close();
            sessionFactory5.close();


            session4.getTransaction().commit();
            session4.close();
            sessionFactory4.close();
        }


    }

    public static class UniqOffers {
        private int storeID;
        private String offerName;
        private String unit;
        private int category;

        public UniqOffers(int storeId, String offerName, String unit, int category) {
            this.storeID = storeId;
            this.offerName = offerName;
            this.unit = unit;
            this.category = category;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UniqOffers that = (UniqOffers) o;
            return storeID == that.storeID && offerName.equals(that.offerName) && unit.equals(that.unit)&& unit.equals(that.category);
        }

        @Override
        public int hashCode() {
            return Objects.hash(storeID, offerName, unit, category);
        }
    }

}

