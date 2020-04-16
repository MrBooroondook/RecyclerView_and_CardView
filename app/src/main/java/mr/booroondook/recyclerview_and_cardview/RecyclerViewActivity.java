package mr.booroondook.recyclerview_and_cardview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public abstract class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    private RecyclerView getRecyclerView() {
        if (recyclerView == null) {
            recyclerView = new RecyclerView(this);
            recyclerView.setHasFixedSize(true);
            setContentView(recyclerView);
        }
        return(recyclerView);
    }

    void setAdapter(RecyclerView.Adapter adapter) {
        getRecyclerView().setAdapter(adapter);
    }

    void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        getRecyclerView().setLayoutManager(layoutManager);
    }
}
