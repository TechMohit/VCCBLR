package grossary.cyron.com.grossaryvccblrrelesed;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class test extends AppCompatActivity {

    private TextView txtSel,txtBack;
    private boolean animateB=true;
    private RelativeLayout relSel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        txtSel=findViewById(R.id.txtSel);
        txtBack=findViewById(R.id.txtBack);
        relSel=findViewById(R.id.relSel);

        txtSel.setBackground(ContextCompat.getDrawable(test.this,R.drawable.man_avatar));
        relSel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(animateB) {

                    ObjectAnimator anim = ObjectAnimator.ofFloat(relSel, "X", txtBack.getX(), txtBack.getWidth()-relSel.getWidth());
                    anim.setDuration(400);                  // Duration in milliseconds
                    anim.start();
                    setAlphaAnimation(txtSel);
                }else{
                    ObjectAnimator anim = ObjectAnimator.ofFloat(relSel, "X", txtBack.getWidth()-relSel.getWidth(), txtBack.getX());
                    anim.setDuration(400);                  // Duration in milliseconds
                    anim.start();
                    setAlphaAnimation(txtSel);
                }
            }
        });
    }

    public  void setAlphaAnimation(final View v) {
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(v, "alpha",  1f, .3f);
        fadeOut.setDuration(200);
        fadeOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                if(animateB){
                    v.setBackground(ContextCompat.getDrawable(test.this,R.drawable.woman_avatar_selected));
                    animateB=false;
                }else{
                    animateB=true;
                    v.setBackground(ContextCompat.getDrawable(test.this,R.drawable.man_avatar));

                }
                onFadeOutEnd(v);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if(animateB){
                    v.setBackground(ContextCompat.getDrawable(test.this,R.drawable.woman_avatar_selected));
                    animateB=false;
                }else{
                    animateB=true;
                    v.setBackground(ContextCompat.getDrawable(test.this,R.drawable.man_avatar));
                }
                onFadeOutEnd(v);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationPause(Animator animation) {
                super.onAnimationPause(animation);
            }

            @Override
            public void onAnimationResume(Animator animation) {
                super.onAnimationResume(animation);
            }
        });
        fadeOut.start();

    }

    private void onFadeOutEnd(View v) {
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(v, "alpha", .3f, 1f);
        fadeIn.setDuration(200);
        fadeIn.start();
    }

}
