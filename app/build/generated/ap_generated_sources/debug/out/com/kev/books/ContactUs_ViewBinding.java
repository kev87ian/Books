// Generated code from Butter Knife. Do not modify!
package com.kev.books;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ContactUs_ViewBinding implements Unbinder {
  private ContactUs target;

  @UiThread
  public ContactUs_ViewBinding(ContactUs target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ContactUs_ViewBinding(ContactUs target, View source) {
    this.target = target;

    target.editTextName = Utils.findRequiredViewAsType(source, R.id.editTextName, "field 'editTextName'", EditText.class);
    target.editTextEmail = Utils.findRequiredViewAsType(source, R.id.editTextEmail, "field 'editTextEmail'", EditText.class);
    target.submitMessageButton = Utils.findRequiredViewAsType(source, R.id.submitMessageButton, "field 'submitMessageButton'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ContactUs target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.editTextName = null;
    target.editTextEmail = null;
    target.submitMessageButton = null;
  }
}
