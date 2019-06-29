package com.slin.camera_transfer.dialog;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.slin.camera_transfer.R;
import com.slin.camera_transfer.config.SettingConfig;
import com.slin.dialog.core.BaseSlinDialog;
import com.slin.dialog.core.DialogViewHolder;
import com.slin.dialog.core.ViewHolder;

import static com.slin.camera_transfer.utils.Utils.parseInteger;

/**
 * author: slin
 * date: 2019-06-29
 * description: 设置界面
 * ip，port
 * width，height
 */
public class SettingViewHolder extends DialogViewHolder {

    private EditText etServerIp;
    private EditText etPort;
    private EditText etWidth;
    private EditText etHeight;
    private Switch swCapture;


    private SettingConfig config;
    private OnConfirmListener confirmListener;


    public SettingViewHolder(SettingConfig config) {
        super(R.layout.dialog_setting);
        this.config = config;
    }

    @Override
    public void onViewConverted(ViewHolder helper, BaseSlinDialog dialog) {
        etServerIp = helper.getView(R.id.et_server_ip);
        etPort = helper.getView(R.id.et_port);
        etWidth = helper.getView(R.id.et_width);
        etHeight = helper.getView(R.id.et_height);
        swCapture = helper.getView(R.id.sw_use_capture);

        helper.setOnClickListener(R.id.btn_cancel, view -> dialog.dismiss());
        helper.setOnClickListener(R.id.btn_ok, view -> updateSetting());

        etServerIp.setText(config.ip);
        etPort.setText(String.valueOf(config.port));
        etWidth.setText(String.valueOf(config.width));
        etHeight.setText(String.valueOf(config.height));
        swCapture.setChecked(config.useCapture);
    }

    private void updateSetting() {
        SettingConfig config = new SettingConfig();
        String ipStr = etServerIp.getText().toString();
        if (TextUtils.isEmpty(ipStr)) {
            showToast("请输入服务ip");
            return;
        }
        config.ip = ipStr;

        int result = parseInteger(etPort.getText().toString());
        if (result == 0) {
            showToast("请输入正确的端口号");
            return;
        }
        config.port = result;

        result = parseInteger(etWidth.getText().toString());
        if (result == 0) {
            showToast("请输入正确的宽度值");
            return;
        }
        config.width = result;

        result = parseInteger(etHeight.getText().toString());
        if (result == 0) {
            showToast("请输入正确的高度值");
            return;
        }
        config.height = result;
        config.useCapture = swCapture.isChecked();

        mDialog.dismiss();

        if (confirmListener != null) {
            confirmListener.onConfirm(config);
        }
    }

    public SettingViewHolder setConfirmListener(OnConfirmListener listener) {
        this.confirmListener = listener;
        return this;
    }

    private void showToast(String msg) {
        Toast.makeText(mDialog.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public interface OnConfirmListener {
        void onConfirm(SettingConfig config);
    }

}
