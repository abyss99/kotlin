package com.lazysoul.kotlinwithandroid.ui.detail;

import com.lazysoul.kotlinwithandroid.R;
import com.lazysoul.kotlinwithandroid.common.BaseActivity;
import com.lazysoul.kotlinwithandroid.common.BaseMvpView;
import com.lazysoul.kotlinwithandroid.datas.Todo;
import com.lazysoul.kotlinwithandroid.singletons.TodoManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by Lazysoul on 2017. 7. 15..
 */

public class DetailActivity extends BaseActivity implements DetailMvpView {

    @Inject
    Realm realm;

    DetailMvpPresenter<DetailMvpView> presenter;

    AppCompatEditText et;

    Intent resultData = new Intent();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setSupportActionBar((Toolbar) findViewById(R.id.tb_activity_detail));

        et = (AppCompatEditText) findViewById(R.id.et_activity_detail);

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.onTextChanged(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        presenter.loadTodo(getIntent());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public void onBackPressed() {
        if (!presenter.isFixed()) {
            super.onBackPressed();
        } else {
            showSaveDialog();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_detail, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem saveMenu = menu.findItem(R.id.menu_activity_main_save);
        saveMenu.setVisible(presenter.isFixed());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_activity_main_save:
                presenter.saveTodo(et.getText().toString());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSaveDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.msg_not_save)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        DetailActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public void inject() {
        component.inject(this);
    }

    @Override
    public void initPresenter() {
        presenter = new DetailMvpPresentImpl<>(realm);
        presenter.attachView(this);
    }

    @Override
    public void onUpdated(Todo todo, boolean editable) {
        et.setText(todo.getBody());
        if (editable) {
            et.requestFocus();
        }
    }

    @Override
    public void onChagedSaveBt() {
        invalidateOptionsMenu();
    }

    @Override
    public void onSaved(int requestType, int todoId) {
        int result = -1;
        switch (requestType) {
            case TodoManager.REQUEST_TYPE_CREATE:
                result = TodoManager.RESULT_TYPE_CREATED;
                break;
            case TodoManager.REQUEST_TYPE_VIEW:
                result = TodoManager.RESULT_TYPE_UPDATED;
                break;
        }

        resultData.putExtra(TodoManager.KEY_RESULT_TYPE, result);
        resultData.putExtra(TodoManager.KEY_ID, todoId);
        setResult(RESULT_OK, resultData);
    }
}
