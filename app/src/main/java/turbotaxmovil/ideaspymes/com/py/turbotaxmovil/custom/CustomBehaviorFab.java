package turbotaxmovil.ideaspymes.com.py.turbotaxmovil.custom;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by cromero on 09/10/2015.
 */
public class CustomBehaviorFab extends CoordinatorLayout.Behavior<FloatingActionButton> {

    public CustomBehaviorFab(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        return dependency instanceof Snackbar.SnackbarLayout || dependency instanceof RelativeLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton fab, View dependency) {
      /*  if (dependency instanceof AppBarLayout) {
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
            int fabBottomMargin = lp.bottomMargin;
            int distanceToScroll = fab.getHeight() + fabBottomMargin;
            float translationY = Math.min(0, dependency.getTranslationY() - dependency.getHeight());
            float ratio = (float)dependency.getY()/(float)50;
            fab.setTranslationY(-distanceToScroll * ratio);
        }*/

        if(dependency.getVisibility() == View.VISIBLE){
            float translationY = Math.min(0, dependency.getTranslationY() - dependency.getHeight());
            fab.setTranslationY(translationY);
        }else if(dependency.getVisibility() == View.GONE){
            fab.setTranslationY(0f);
        }

        return true;
    }
}
