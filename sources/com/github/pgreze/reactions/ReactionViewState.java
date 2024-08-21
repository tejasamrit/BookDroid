package com.github.pgreze.reactions;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0001\u0003\u0006\u0007\b¨\u0006\t"}, mo37160d2 = {"Lcom/github/pgreze/reactions/ReactionViewState;", "", "()V", "Boundary", "Selected", "WaitingSelection", "Lcom/github/pgreze/reactions/ReactionViewState$Boundary;", "Lcom/github/pgreze/reactions/ReactionViewState$WaitingSelection;", "Lcom/github/pgreze/reactions/ReactionViewState$Selected;", "library_release"}, mo37161k = 1, mo37162mv = {1, 4, 0})
/* compiled from: ReactionViewGroup.kt */
public abstract class ReactionViewState {
    private ReactionViewState() {
    }

    public /* synthetic */ ReactionViewState(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\b\tB\u001b\b\u0002\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0001\u0002\n\u000b¨\u0006\f"}, mo37160d2 = {"Lcom/github/pgreze/reactions/ReactionViewState$Boundary;", "Lcom/github/pgreze/reactions/ReactionViewState;", "path", "Lkotlin/Pair;", "", "(Lkotlin/Pair;)V", "getPath", "()Lkotlin/Pair;", "Appear", "Disappear", "Lcom/github/pgreze/reactions/ReactionViewState$Boundary$Appear;", "Lcom/github/pgreze/reactions/ReactionViewState$Boundary$Disappear;", "library_release"}, mo37161k = 1, mo37162mv = {1, 4, 0})
    /* compiled from: ReactionViewGroup.kt */
    public static abstract class Boundary extends ReactionViewState {
        private final Pair<Integer, Integer> path;

        private Boundary(Pair<Integer, Integer> pair) {
            super((DefaultConstructorMarker) null);
            this.path = pair;
        }

        public /* synthetic */ Boundary(Pair pair, DefaultConstructorMarker defaultConstructorMarker) {
            this(pair);
        }

        public final Pair<Integer, Integer> getPath() {
            return this.path;
        }

        @Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, mo37160d2 = {"Lcom/github/pgreze/reactions/ReactionViewState$Boundary$Appear;", "Lcom/github/pgreze/reactions/ReactionViewState$Boundary;", "path", "Lkotlin/Pair;", "", "(Lkotlin/Pair;)V", "library_release"}, mo37161k = 1, mo37162mv = {1, 4, 0})
        /* compiled from: ReactionViewGroup.kt */
        public static final class Appear extends Boundary {
            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Appear(Pair<Integer, Integer> pair) {
                super(pair, (DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(pair, "path");
            }
        }

        @Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, mo37160d2 = {"Lcom/github/pgreze/reactions/ReactionViewState$Boundary$Disappear;", "Lcom/github/pgreze/reactions/ReactionViewState$Boundary;", "selectedView", "Lcom/github/pgreze/reactions/ReactionView;", "path", "Lkotlin/Pair;", "", "(Lcom/github/pgreze/reactions/ReactionView;Lkotlin/Pair;)V", "getSelectedView", "()Lcom/github/pgreze/reactions/ReactionView;", "library_release"}, mo37161k = 1, mo37162mv = {1, 4, 0})
        /* compiled from: ReactionViewGroup.kt */
        public static final class Disappear extends Boundary {
            private final ReactionView selectedView;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Disappear(ReactionView reactionView, Pair<Integer, Integer> pair) {
                super(pair, (DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(pair, "path");
                this.selectedView = reactionView;
            }

            public final ReactionView getSelectedView() {
                return this.selectedView;
            }
        }
    }

    @Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, mo37160d2 = {"Lcom/github/pgreze/reactions/ReactionViewState$WaitingSelection;", "Lcom/github/pgreze/reactions/ReactionViewState;", "()V", "library_release"}, mo37161k = 1, mo37162mv = {1, 4, 0})
    /* compiled from: ReactionViewGroup.kt */
    public static final class WaitingSelection extends ReactionViewState {
        public static final WaitingSelection INSTANCE = new WaitingSelection();

        private WaitingSelection() {
            super((DefaultConstructorMarker) null);
        }
    }

    @Metadata(mo37158bv = {1, 0, 3}, mo37159d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, mo37160d2 = {"Lcom/github/pgreze/reactions/ReactionViewState$Selected;", "Lcom/github/pgreze/reactions/ReactionViewState;", "view", "Lcom/github/pgreze/reactions/ReactionView;", "(Lcom/github/pgreze/reactions/ReactionView;)V", "getView", "()Lcom/github/pgreze/reactions/ReactionView;", "library_release"}, mo37161k = 1, mo37162mv = {1, 4, 0})
    /* compiled from: ReactionViewGroup.kt */
    public static final class Selected extends ReactionViewState {
        private final ReactionView view;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Selected(ReactionView reactionView) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(reactionView, "view");
            this.view = reactionView;
        }

        public final ReactionView getView() {
            return this.view;
        }
    }
}
