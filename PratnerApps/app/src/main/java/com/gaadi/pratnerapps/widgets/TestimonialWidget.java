package com.gaadi.pratnerapps.widgets;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gaadi.pratnerapps.R;
import com.gaadi.pratnerapps.models.HomeApiModel;
import com.gaadi.pratnerapps.models.TestimonialApiModel;
import com.gaadi.pratnerapps.utils.Constants;
import com.gaadi.pratnerapps.utils.DatabaseUtil;
import com.gaadi.pratnerapps.utils.Utils;

import java.util.ArrayList;

/**
 * Created by vinodtakhar on 25/5/16.
 */
public class TestimonialWidget extends BaseCarouselWidget<TestimonialApiModel.TestimonialModel> {
    private HomeApiModel.TestimonialWidgetModel testimonialWidgetModel;
    private ArrayList<TestimonialApiModel.TestimonialModel> testimonials;
    View  viewInflaterLayout;

    public TestimonialWidget(Context context, HomeApiModel.TestimonialWidgetModel testimonialWidgetModel) {
        super(context);
        this.testimonialWidgetModel = testimonialWidgetModel;
        this.testimonials = DatabaseUtil.getTopTestimonial(context,testimonialWidgetModel.getNumberOfSlides());
        String styleView = testimonialWidgetModel.getConfig().getStyle().getView();
        if(styleView!=null && styleView.equals(Constants.STYLE_VIEW))
            viewInflaterLayout=LayoutInflater.from(context).inflate(R.layout.testimonial_horzontial_list,null);
        else
            viewInflaterLayout=LayoutInflater.from(context).inflate(R.layout.testinomial_layout,null);

        if(testimonialWidgetModel.getTitle()!=null)
            setTitle(testimonialWidgetModel.getTitle());
           setShowBottomCard(false);

        init();
    }

    @Override
    public int getSize() {
        return testimonials.size();
    }

    @Override
    public boolean isAutoScroll() {
        return testimonialWidgetModel.isAutoSlide();
    }

    @Override
    public View getViewAt(int position) {
        View view = viewInflaterLayout;
        TestimonialApiModel.TestimonialModel testimonialModel = getItemAt(position);

        ViewHolder viewHolder = new ViewHolder(view);

        String showImage = testimonialWidgetModel.getConfig().getOptions().getUsers_image();

        if(showImage!=null && showImage.equals("Show"))
            Utils.setCircularImageView(context, testimonialModel.getImage_name(), viewHolder.ivIcon);
        else
            viewHolder.ivIcon.setVisibility(View.GONE);

        if(testimonialModel.getName()!=null && testimonialModel.getName().trim().length()>0)
            viewHolder.tvName.setText(testimonialModel.getName());

      /*  if(Utils.isBlank(testimonialModel.getRole())){
            viewHolder.tvRole.setVisibility(View.GONE);
        }else {
            viewHolder.tvRole.setVisibility(View.VISIBLE);
            viewHolder.tvRole.setText(testimonialModel.getRole());
        }*/
        viewHolder.tvTestimonial.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.tvTestimonial.setMaxLines(3);
        viewHolder.tvTestimonial.setText(testimonialModel.getTestimonial());


        view.setTag(viewHolder);

        return view;
    }

    private static class ViewHolder{
        ImageView ivIcon;
        TextView tvName,tvRole,tvTestimonial;

        ViewHolder(View view){
            ivIcon = (ImageView) view.findViewById(R.id.ivIcon);
            tvName = (TextView)view.findViewById(R.id.tvName);
           // tvRole = (TextView)view.findViewById(R.id.tvRole);
            tvTestimonial = (TextView)view.findViewById(R.id.tvTestimonial);
        }
    }

    @Override
    public TestimonialApiModel.TestimonialModel getItemAt(int position) {
        return testimonials.get(position);
    }
}
