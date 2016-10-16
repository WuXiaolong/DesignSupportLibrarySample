package com.wuxiaolong.designsupportlibrarysample;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ThirdFragment extends BaseFragment {
    private Toolbar mToolbar;
    View thirdLayout;
    SwitchCompat switchCompat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setTitle("其他组件");
        ((MainActivity) getActivity()).initDrawer(mToolbar);
        thirdLayout = view.findViewById(R.id.thirdLayout);
        switchCompat = (SwitchCompat) view.findViewById(R.id.switchCompat);
        view.findViewById(R.id.snackbars).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(thirdLayout, "Snackbar Test", Snackbar.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.bottomSheetDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog();
            }
        });
        view.findViewById(R.id.switchCompatLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchCompat.setChecked(!switchCompat.isChecked());
            }
        });
    }

    private BottomSheetDialog mBottomSheetDialog;

    public void showBottomSheetDialog() {
        View sheetDialogView = View.inflate(mActivity, R.layout.sheet_dialog, null);
        mBottomSheetDialog = new BottomSheetDialog(mActivity);
        mBottomSheetDialog.setContentView(sheetDialogView);
        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mBottomSheetDialog = null;
            }
        });
        mBottomSheetDialog.show();
    }
}
