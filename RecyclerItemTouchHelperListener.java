package alexnewcomer.cs360.com.Interface;

import androidx.recyclerview.widget.RecyclerView;

//sets for what to do when item on the click is swiped
public interface RecyclerItemTouchHelperListener {
    void onSwiped(RecyclerView.ViewHolder viewHolder,int direction,int position);

}
