/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.support.android.designlibdemo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.support.android.designlibdemo.activities.CategoryObjectActivity;
import com.support.android.designlibdemo.activities.SignsActivity;
import com.support.android.designlibdemo.model.Category;
import com.support.android.designlibdemo.model.CategoryObject;
import com.support.android.designlibdemo.model.Question;

import java.util.List;

public class CategoryFragment extends Fragment {

    Category data;

    RecyclerView rv;
    TextView description;
    TextView title;
    Button link;
    LinearLayout questions;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.category_fragment_layout, container, false);
        rv = (RecyclerView) root.findViewById(R.id.recyclerview);
        title = (TextView) root.findViewById(R.id.category_title);
        description = (TextView) root.findViewById(R.id.category_description);
        questions = (LinearLayout) root.findViewById(R.id.category_question_container);
        link = (Button) root.findViewById(R.id.category_link_button);

        Bundle bundle = this.getArguments();
        data = bundle.getParcelable("data");
        setData();
        return root;
    }

    private void setData() {
        if (data != null) {
            if (data.getTextTitle() != null) title.setText(data.getTextTitle());
            else title.setVisibility(View.GONE);
            if (data.getDescription() != null) description.setText(data.getDescription());
            else description.setVisibility(View.GONE);
            if (data.getItems() != null) setupRecyclerView(rv, data);
            else rv.setVisibility(View.GONE);

            if(data.getQuestions() != null){
                for (Question q : data.getQuestions()){
                    QuestionView qw = new QuestionView(getActivity());
                    qw.setValues(q.getQuestion(), q.getAnswer());
                    questions.addView(qw);
                }
            } else {
                questions.setVisibility(View.GONE);
            }

            if (data.getLinkToSite() != null){
                link.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse(data.getLinkToSite());
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });
            } else {
                link.setVisibility(View.GONE);
            }
        }
    }

    private void setupRecyclerView(RecyclerView recyclerView, Category category) {
        Log.d("CATEGORY O", "setup Recycler");
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new SimpleRecyclerViewAdapter(getActivity(),
                category.getItems()));
    }

    public class SimpleRecyclerViewAdapter extends RecyclerView.Adapter<SimpleRecyclerViewAdapter.ViewHolder> {

        private final TypedValue mTypedValue = new TypedValue();
        private int mBackground;
        private List<CategoryObject> mValues;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public String mBoundString;

            public final View mView;
            public final ImageView mImageView;
            public final TextView mTextView;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mImageView = (ImageView) view.findViewById(R.id.avatar);
                mTextView = (TextView) view.findViewById(android.R.id.text1);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mTextView.getText();
            }
        }

        public CategoryObject getValueAt(int position) {
            return mValues.get(position);
        }

        public SimpleRecyclerViewAdapter(Context context, List<CategoryObject> items) {
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            mBackground = mTypedValue.resourceId;
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            view.setBackgroundResource(mBackground);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.mBoundString = data.getTitle();
            holder.mTextView.setText(mValues.get(position).getTitle());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    if (data.getTitle().toLowerCase().equals("знакови")){
                        Intent intent = new Intent(context, SignsActivity.class);
                        intent.putExtra("sign", mValues.get(position).getSign());
                        context.startActivity(intent);
                    } else {
                        Intent intent = new Intent(context, CategoryObjectActivity.class);
                        intent.putExtra(CategoryObjectActivity.EXTRA_NAME, holder.mBoundString);
                        intent.putExtra(CategoryObjectActivity.EXTRA_DATA, mValues.get(position));
                        context.startActivity(intent);
                    }
                }
            });
            if (mValues.get(position).getImage() != 0){
                Glide.with(holder.mImageView.getContext())
                        .load(mValues.get(position).getImage())
                        .fitCenter()
                        .into(holder.mImageView);
            } else {
                holder.mImageView.setVisibility(View.GONE);
            }
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }
    }
}
