package com.gaadi.pratnerapps.models;

import java.util.ArrayList;

/**
 * Created by vinodtakhar on 5/7/16.
 */
public class MenuApiModel {
    private ArrayList<MenuModel> items;
    private MenuConfig config;

    public ArrayList<MenuModel> getItems() {
        return items;
    }

    public void setItems(ArrayList<MenuModel> items) {
        this.items = items;
    }

    public MenuConfig getConfig() {
        return config;
    }

    public void setConfig(MenuConfig config) {
        this.config = config;
    }

    public static class MenuConfig{
        public static final String STYLE_HUMBERGER = "Hamburger";
        public static final String STYLE_BOTTOM = "Horizontal";
        public static final String STYLE_LIST = "List";
        public static final String STYLE_MATRIX  = "Matrix";
        public static final String STYLE_FAB_MENU = "Corner_Hamburger";

        private MenuStyle style;
        private Options options;

        public Options getOptions() {
            return options;
        }

        public void setOptions(Options options) {
            this.options = options;
        }

        public MenuStyle getStyle() {
            return style;
        }

        public void setStyle(MenuStyle style) {
            this.style = style;
        }

        public String getMenuStyle(){
            if(style!=null) {
                return style.getLayout();
            }
            else{ return STYLE_HUMBERGER;}
        }

        public static class MenuStyle{
            private String layout;

            public String getLayout() {
                return layout;
            }

            public void setLayout(String layout) {
                this.layout = layout;
            }
        }
        public static class Options{
            private String position;
            private String text;
            private String background;
            private String border;
            private String soft_corner;
            private String alignment;
            private String icons;
            private String text_alignment;
            private int mat_rows;
            private int mat_cols;

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getBackground() {
                return background;
            }

            public void setBackground(String background) {
                this.background = background;
            }

            public String getBorder() {
                return border;
            }

            public void setBorder(String border) {
                this.border = border;
            }

            public String getSoft_corner() {
                return soft_corner;
            }

            public void setSoft_corner(String soft_corner) {
                this.soft_corner = soft_corner;
            }

            public String getAlignment() {
                return alignment;
            }

            public void setAlignment(String alignment) {
                this.alignment = alignment;
            }

            public String getIcons() {
                return icons;
            }

            public void setIcons(String icons) {
                this.icons = icons;
            }

            public String getText_alignment() {
                return text_alignment;
            }

            public void setText_alignment(String text_alignment) {
                this.text_alignment = text_alignment;
            }

            public int getMat_rows() {
                return mat_rows;
            }

            public void setMat_rows(int mat_rows) {
                this.mat_rows = mat_rows;
            }

            public int getMat_cols() {
                return mat_cols;
            }

            public void setMat_cols(int mat_cols) {
                this.mat_cols = mat_cols;
            }
        }
    }
    public static class MenuModel {
        private String  key;
        private String value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
