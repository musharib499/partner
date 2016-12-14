package com.gaadi.pratnerapps.models;

import java.util.ArrayList;

/**
 * Created by vinodtakhar on 3/8/16.
 */
public class InsuranceApiModel extends FinanceApiModel{
    private ArrayList<String> companies;

    public ArrayList<String> getCompanies() {
        return companies;
    }

    public void setCompanies(ArrayList<String> companies) {
        this.companies = companies;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InsuranceApiModel{");
        sb.append("companies=").append(companies);
        sb.append("heroImage=").append(getHeroImage());
        sb.append("partners=").append(getPartners());
        sb.append('}');
        return sb.toString();
    }
}
