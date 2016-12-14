package com.gaadi.pratnerapps.models;

import java.util.ArrayList;

/**
 * Created by vinodtakhar on 2/6/16.
 */
public class MakeApiModel {
    private ArrayList<MakeModel> items;

    public ArrayList<MakeModel> getItems() {
        return items;
    }

    public void setItems(ArrayList<MakeModel> items) {
        this.items = items;
    }

    public class MakeModel {
        private int id;
        private String make;
        private int rank;
        private int in_stock = 1;
        private ArrayList<Model> model;

        public int getIn_stock() {
            return in_stock;
        }

        public void setIn_stock(int in_stock) {
            this.in_stock = in_stock;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMake() {
            return make;
        }

        public void setMake(String make) {
            this.make = make;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public ArrayList<Model> getModel() {
            return model;
        }

        public void setModel(ArrayList<Model> model) {
            this.model = model;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("MakeModel{");
            sb.append("id=").append(id);
            sb.append(", make='").append(make).append('\'');
            sb.append(", rank=").append(rank);
            sb.append(", in_stock=").append(in_stock);
            sb.append(", model=").append(model);
            sb.append('}');
            return sb.toString();
        }

        public class Model {
            private int id;
            private String model;
            private int rank;
            private int in_stock = 1;
            private ArrayList<Version> version;

            public int getIn_stock() {
                return in_stock;
            }

            public void setIn_stock(int in_stock) {
                this.in_stock = in_stock;
            }

            public ArrayList<Version> getVersion() {
                return version;
            }

            public void setVersion(ArrayList<Version> version) {
                this.version = version;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public int getRank() {
                return rank;
            }

            public void setRank(int rank) {
                this.rank = rank;
            }

            public class Version {
                private int id;
                private String version;
                private String fuel;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getVersion() {
                    return version;
                }

                public void setVersion(String version) {
                    this.version = version;
                }

                public String getFuel() {
                    return fuel;
                }

                public void setFuel(String fuel) {
                    this.fuel = fuel;
                }
            }
        }
    }
}
