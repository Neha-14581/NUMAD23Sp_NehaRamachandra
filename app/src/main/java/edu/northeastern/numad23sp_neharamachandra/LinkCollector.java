package edu.northeastern.numad23sp_neharamachandra;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;

import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.net.URL;
import java.util.regex.Pattern;

public class LinkCollector extends AppCompatActivity {
    ArrayList<LinkModel> arrLinks = new ArrayList<>();

    RecyclerView recyclerview2;
    RecyclerLinkAdapter adapter;

    RelativeLayout layout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_collector);


        recyclerview2 = findViewById(R.id.recyclerLinks1);
        FloatingActionButton btnOpenDialog1 = findViewById(R.id.btnOpenDialog);

        layout = findViewById(R.id.relLayout);


        btnOpenDialog1.setOnClickListener(v -> {
            Dialog dialog = new Dialog(LinkCollector.this);
            dialog.setContentView(R.layout.activity_link_model);
            EditText newName = dialog.findViewById(R.id.newName);
            EditText newLink = dialog.findViewById(R.id.newLink);
            Button btnAction = dialog.findViewById(R.id.btnAction);

            btnAction.setOnClickListener(v1 -> {
                String name = newName.getText().toString();
                String link = newLink.getText().toString();
                String message = "";
                if (checkURL(link)) {
                    arrLinks.add(new LinkModel(name,link));
                    adapter.notifyItemInserted(arrLinks.size()-1);
                    recyclerview2.scrollToPosition(arrLinks.size()-1);
                    message = "Successfully added a new link";
                    dialog.dismiss();
                }
                else{
                    message = "Invalid Url Format";
                }

                Snackbar snack = Snackbar.make(layout, message, Snackbar.LENGTH_LONG).setAction("CLOSE", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getApplicationContext(), "Closing", Toast.LENGTH_SHORT).show();
                    }
                });
                snack.show();
            });
            dialog.show();
        });


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                View parentLayout = findViewById(android.R.id.content);
                Snackbar snack = Snackbar.make(parentLayout, "Deleted a link", Snackbar.LENGTH_LONG).setAction("Action", null);
                View snackView = snack.getView();
                TextView mTextView = snackView.findViewById(com.google.android.material.R.id.snackbar_text);
                mTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                snack.show();
                int position = viewHolder.getLayoutPosition();
                arrLinks.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerview2);

        recyclerview2.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RecyclerLinkAdapter(this, arrLinks);

        recyclerview2.setAdapter(adapter);
    }

    public static boolean checkURL(CharSequence input) {
        if (TextUtils.isEmpty(input)) {
            return false;
        }
        Pattern URL_PATTERN = Patterns.WEB_URL;
        boolean isURL = URL_PATTERN.matcher(input).matches();
        if (!isURL) {
            String urlString = input + "";
            if (URLUtil.isNetworkUrl(urlString)) {
                try {
                    new URL(urlString);
                    isURL = true;
                } catch (Exception e) {
                }
            }
        }
        return isURL;
    }
}