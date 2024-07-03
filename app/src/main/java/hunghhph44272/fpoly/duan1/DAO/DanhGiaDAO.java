package hunghhph44272.fpoly.duan1.DAO;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import hunghhph44272.fpoly.duan1.R;


public class DanhGiaDAO extends LinearLayout {
    private ImageView[] stars;
    private int rating=0;
    public DanhGiaDAO(Context context) {
        super(context);
        init();
    }

    public DanhGiaDAO(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.layout_danhgia,this);
        stars=new ImageView[]{
                findViewById(R.id.star1),
                findViewById(R.id.star2),
                findViewById(R.id.star3),
                findViewById(R.id.star4),
                findViewById(R.id.star5)
        };
        for(int i=0;i<stars.length;i++){
            final int position=i;
            stars[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    setRating(position+1);
                }
            });
        }
    }
    public void setRating(int rating){
        if(rating<0||rating>stars.length)
            return;
        this.rating=rating;
        for(int i=0;i<stars.length;i++){
            if(i<rating){
                stars[i].setImageResource(R.drawable.baseline_star_rate_24);
            }else{
                stars[i].setImageResource(R.drawable.baseline_star_outline_24);
            }
        }
    }
    public int getRating(){
        return rating;
    }
}
