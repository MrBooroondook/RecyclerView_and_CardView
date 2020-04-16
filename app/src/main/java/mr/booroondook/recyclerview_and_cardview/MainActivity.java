package mr.booroondook.recyclerview_and_cardview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends RecyclerViewActivity {
    private String[] lastNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lastNames = getResources().getStringArray(R.array.last_names);

        setLayoutManager(new LinearLayoutManager(this));
        setAdapter(new RecyclerAdapter());
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<Holder> {

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new Holder(getLayoutInflater()
                    .inflate(R.layout.list_cell, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            holder.bindData(lastNames[position]);
        }

        @Override
        public int getItemCount() {
            return lastNames.length;
        }
    }

    private static class Holder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        final TextView unitName;
        final TextView unitStatus;
        final ImageView iconStatus;
        private String lastName;

        Holder(@NonNull View itemView) {
            super(itemView);
            unitName = itemView.findViewById(R.id.unit_name);
            unitStatus = itemView.findViewById(R.id.unit_status);
            iconStatus = itemView.findViewById(R.id.icon_status);
            itemView.setOnClickListener(this);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                itemView.setOnTouchListener(new View.OnTouchListener() {
                    @SuppressLint("ClickableViewAccessibility")
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        view.findViewById(R.id.linear_layout_cell).getBackground()
                                .setHotspot(motionEvent.getX(), motionEvent.getY());
                        return false;
                    }
                });
            }
        }

        void bindData(String lastName) {
            this.lastName = lastName;
            unitName.setText(lastName);

            if (lastName.length() % 2 == 0) {
                unitStatus.setText(R.string.adopted);
                iconStatus.setImageResource(R.drawable.ic_check_24dp);
            } else {
                unitStatus.setText(R.string.fired);
                iconStatus.setImageResource(R.drawable.ic_close_24dp);
            }
        }

        @Override
        public void onClick(View view) {
            String status;
            if (lastName.length() % 2 == 0) {
                status = view.getContext().getResources().getString(R.string.adopted);
            } else {
                status = view.getContext().getResources().getString(R.string.fired);
            }
            Toast.makeText(view.getContext(),
                    String.format("%s:\n%s", lastName, status),
                    Toast.LENGTH_SHORT).show();
        }
    }
}
