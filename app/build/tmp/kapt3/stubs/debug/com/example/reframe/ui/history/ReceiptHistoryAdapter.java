package com.example.reframe.ui.history;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0001\u0011B-\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\t\u001a\u00020\u00062\n\u0010\n\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001c\u0010\r\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\fH\u0016R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/example/reframe/ui/history/ReceiptHistoryAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/example/reframe/data/dto/ReceiptHistoryResponse;", "Lcom/example/reframe/ui/history/ReceiptHistoryAdapter$ReceiptViewHolder;", "onWriteReviewClick", "Lkotlin/Function1;", "", "onShowReviewClick", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ReceiptViewHolder", "app_debug"})
public final class ReceiptHistoryAdapter extends androidx.recyclerview.widget.ListAdapter<com.example.reframe.data.dto.ReceiptHistoryResponse, com.example.reframe.ui.history.ReceiptHistoryAdapter.ReceiptViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.example.reframe.data.dto.ReceiptHistoryResponse, kotlin.Unit> onWriteReviewClick = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.example.reframe.data.dto.ReceiptHistoryResponse, kotlin.Unit> onShowReviewClick = null;
    
    public ReceiptHistoryAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.example.reframe.data.dto.ReceiptHistoryResponse, kotlin.Unit> onWriteReviewClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.example.reframe.data.dto.ReceiptHistoryResponse, kotlin.Unit> onShowReviewClick) {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.example.reframe.ui.history.ReceiptHistoryAdapter.ReceiptViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.example.reframe.ui.history.ReceiptHistoryAdapter.ReceiptViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/example/reframe/ui/history/ReceiptHistoryAdapter$ReceiptViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/example/reframe/databinding/ItemReceiptHistoryBinding;", "(Lcom/example/reframe/ui/history/ReceiptHistoryAdapter;Lcom/example/reframe/databinding/ItemReceiptHistoryBinding;)V", "bind", "", "item", "Lcom/example/reframe/data/dto/ReceiptHistoryResponse;", "app_debug"})
    public final class ReceiptViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.example.reframe.databinding.ItemReceiptHistoryBinding binding = null;
        
        public ReceiptViewHolder(@org.jetbrains.annotations.NotNull()
        com.example.reframe.databinding.ItemReceiptHistoryBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.example.reframe.data.dto.ReceiptHistoryResponse item) {
        }
    }
}