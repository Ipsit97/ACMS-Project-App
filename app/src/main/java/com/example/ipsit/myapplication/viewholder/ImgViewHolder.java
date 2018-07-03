package com.example.ipsit.myapplication.viewholder;

/**
 * Created by Ipsit on 5/28/2018.
 */




import android.support.v7.widget.RecyclerView;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.example.ipsit.myapplication.R;

public class ImgViewHolder extends RecyclerView.ViewHolder {

    public TextView nameView1;
    public TextView nameView2;
    public TextView nameView3;
    public TextView nameView4;
    public ImageView imageView;

    public ImgViewHolder(View itemView) {
        super(itemView);

        nameView1 = (TextView) itemView.findViewById(R.id.tv_img_name1);
        nameView2 = (TextView) itemView.findViewById(R.id.tv_img_name2);
        nameView3 = (TextView) itemView.findViewById(R.id.tv_img_name3);
        nameView4 = (TextView) itemView.findViewById(R.id.tv_img_name4);
        imageView = (ImageView) itemView.findViewById(R.id.img_view);
    }
}