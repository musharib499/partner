package com.gaadi.pratnerapps.models;

import java.util.ArrayList;

/**
 * Created by vinodtakhar on 16/6/16.
 */
public class HomeApiModel {
    private HeroModel hero;
    private BannerWidgetModel banner;
    private ShowroomWidgetModel showroom;
    private RecentListingWidgetModel recent_listing;
    private TestimonialWidgetModel testimonial;
    private BrandBodyWidgetModel brands;
    private BrandBodyWidgetModel body;
    private ServicesWidgetModel services;

    public HeroModel getHero() {
        return hero;
    }

    public void setHero(HeroModel hero) {
        this.hero = hero;
    }

    public BannerWidgetModel getBanner() {
        return banner;
    }

    public void setBanner(BannerWidgetModel banner) {
        this.banner = banner;
    }

    public ShowroomWidgetModel getShowroom() {
        return showroom;
    }

    public void setShowroom(ShowroomWidgetModel showroom) {
        this.showroom = showroom;
    }

    public RecentListingWidgetModel getRecent_listing() {
        return recent_listing;
    }

    public void setRecent_listing(RecentListingWidgetModel recent_listing) {
        this.recent_listing = recent_listing;
    }

    public TestimonialWidgetModel getTestimonial() {
        return testimonial;
    }

    public void setTestimonial(TestimonialWidgetModel testimonial) {
        this.testimonial = testimonial;
    }

    public BrandBodyWidgetModel getBrands() {
        return brands;
    }

    public void setBrands(BrandBodyWidgetModel brands) {
        this.brands = brands;
    }

    public BrandBodyWidgetModel getBody() {
        return body;
    }

    public void setBody(BrandBodyWidgetModel body) {
        this.body = body;
    }

    public ServicesWidgetModel getServices() {
        return services;
    }

    public void setServices(ServicesWidgetModel services) {
        this.services = services;
    }

    public static class BaseWidgetModel<T extends BaseWidgetConfig,S>{
        private T config;
        private ArrayList<S> items;

        public T getConfig() {
            return config;
        }

        public void setConfig(T config) {
            this.config = config;
        }

        public ArrayList<S> getItems() {
            return items;
        }

        public void setItems(ArrayList<S> items) {
            this.items = items;
        }

        public boolean hasItems(){
            return items!=null && items.size()>0;
        }

        public int getOrder(){
            return config.getOrder();
        }
        public String getTitle(){
            return config.getTitle();
        }

        public boolean isAutoSlide(){
            return config!=null && config.isAutoSlide();
        }
        public int getNumberOfSlides(){
            return config!=null ? config.getNumberOfSlides():0;
        }
    }
    public static class BaseWidgetConfig<T extends BaseWidgetStyle,S extends BaseWidgetOptions>{
        private int order;
        private String title;
        private T style;
        private S options;

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public T getStyle() {
            return style;
        }

        public void setStyle(T style) {
            this.style = style;
        }

        public S getOptions() {
            return options;
        }

        public void setOptions(S options) {
            this.options = options;
        }

        public boolean isAutoSlide(){
            return (options!=null && options.getSlide()!=null && (options.getSlide().equals("Both") || options.getSlide().equals("Auto")));
        }
        public int getNumberOfSlides(){
            return options!=null ? options.getNumber_of_slides() : 0 ;
        }
    }
    public static class BaseWidgetStyle{

    }
    public static class BaseWidgetOptions{
        private String slide;
        private int number_of_slides;

        public String getSlide() {
            return slide;
        }

        public void setSlide(String slide) {
            this.slide = slide;
        }

        public int getNumber_of_slides() {
            return number_of_slides;
        }

        public void setNumber_of_slides(int number_of_slides) {
            this.number_of_slides = number_of_slides;
        }
    }

    public static class HeroModel extends BaseWidgetModel<BaseWidgetConfig<BaseWidgetStyle,BaseWidgetOptions>,HeroModel.HeroItem>{
        public static class HeroItem{
            private String image_url;
            private String image_text;

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public String getImage_text() {
                return image_text;
            }

            public void setImage_text(String image_text) {
                this.image_text = image_text;
            }
        }
    }

    public static class BannerWidgetModel extends BaseWidgetModel<BaseWidgetConfig<BaseWidgetStyle,BaseWidgetOptions>,String>{
    }

    public static class ServicesWidgetModel extends BaseWidgetModel<BaseWidgetConfig<BaseWidgetStyle,BaseWidgetOptions>,String>{
    }

    public static class ShowroomWidgetModel extends BaseWidgetModel<BaseWidgetConfig<BaseWidgetStyle,BaseWidgetOptions>,String>{
    }

    public static class RecentListingWidgetModel extends BaseWidgetModel<BaseWidgetConfig<RecentListingWidgetModel.WidgetStyle,BaseWidgetOptions>,String>{

        public static class WidgetStyle extends BaseWidgetStyle{
            public String getListing_type() {
                return listing_type;
            }

            public void setListing_type(String listing_type) {
                this.listing_type = listing_type;
            }

            private String listing_type;

             }
    }

    public static class TestimonialWidgetModel extends BaseWidgetModel<BaseWidgetConfig<TestimonialWidgetModel.WidgetStyle,TestimonialWidgetModel.WidgetOptions>,String>{
        public static class WidgetOptions extends BaseWidgetOptions{
            private String users_image;
            private String star_rating;

            public String getUsers_image() {
                return users_image;
            }

            public void setUsers_image(String users_image) {
                this.users_image = users_image;
            }

            public String getStar_rating() {
                return star_rating;
            }

            public void setStar_rating(String star_rating) {
                this.star_rating = star_rating;
            }
        }
        public static class WidgetStyle extends BaseWidgetStyle{
            private String view;

            public String getView() {
                return view;
            }

            public void setView(String view) {
                this.view = view;
            }
        }
    }

    public static class ImagesWidgetModel extends BaseWidgetModel<BaseWidgetConfig<BaseWidgetStyle,BaseWidgetOptions>,String>{
    }

    public static class BrandBodyWidgetModel extends BaseWidgetModel<BaseWidgetConfig<BaseWidgetStyle,BaseWidgetOptions>,BrandBodyWidgetModel.Item>{
        public static class Item{
            private String image_url;
            private String key;

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }
        }
    }
}
