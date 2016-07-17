package com.armxyitao.testcoordinatorlayout;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author 熊亦涛
 * @time 16/7/14  09:00
 * @desc ${TODD}
 */
public class FragmentFactory {
    private static Context mContext;
    public static Fragment createFragment(String text, Context context) {
        MyFragment fragment = new MyFragment(context, text);
        mContext = context;
        return fragment;
    }

    public static class MyFragment extends Fragment {
        private Context mContext;
        private String mText;

        public MyFragment(Context context, String text) {
            mContext = context;
            mText = text;
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            RecyclerView rv= (RecyclerView) View.inflate(mContext, R.layout.text, null);
            rv.setLayoutManager(new LinearLayoutManager(mContext));
            rv.setAdapter(new MyAdapter());
            return rv;
        }
    }

    static class MyHolder extends RecyclerView.ViewHolder{
        public TextView tv;
        public MyHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView;
        }
    }
    static class MyAdapter extends RecyclerView.Adapter<MyHolder>{

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView textView = new TextView(mContext);
            return new MyHolder(textView);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            holder.tv.setText("文本"+position);
        }

        @Override
        public int getItemCount() {
            return 100;
        }
    }

//
//    private int targetId;
//    public class MyBehavior extends CoordinatorLayout.Behavior{
//        public MyBehavior(Context context, AttributeSet attrs) {
//            super(context, attrs);
//            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.myapp);
//            for (int i = 0; i < a.getIndexCount(); i++) {
//                int attr = a.getIndex(i);
//                if(a.getIndex(i) == R.styleable.myapp_target){
//                    targetId = a.getResourceId(attr, -1);
//                }
//            }
//            a.recycle();
//        }
//
//        @Override
//        public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
//
//            return dependency.getId()==R.id.vp;
//        }
//
//        @Override
//        public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
//            return true;
//        }
//
//        @Override
//        public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
//            if(dy>0) {
//                Animation a = new TranslateAnimation(1,0,1,0,1,0,1,child.getHeight());
//                a.setDuration(1000);
//                child.startAnimation(a);
//            }else {
//                Animation a = new TranslateAnimation(1,0,1,0,1,child.getHeight(),1,0);
//                a.setDuration(1000);
//                child.startAnimation(a);
//            }
//        }
//    }
}
