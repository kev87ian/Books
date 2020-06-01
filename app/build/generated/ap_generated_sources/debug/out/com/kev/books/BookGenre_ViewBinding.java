// Generated code from Butter Knife. Do not modify!
package com.kev.books;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BookGenre_ViewBinding implements Unbinder {
  private BookGenre target;

  @UiThread
  public BookGenre_ViewBinding(BookGenre target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BookGenre_ViewBinding(BookGenre target, View source) {
    this.target = target;

    target.mBookInput = Utils.findRequiredViewAsType(source, R.id.bookInput, "field 'mBookInput'", EditText.class);
    target.mAuthorText = Utils.findRequiredViewAsType(source, R.id.authorText, "field 'mAuthorText'", TextView.class);
    target.mTitleText = Utils.findRequiredViewAsType(source, R.id.TitleText, "field 'mTitleText'", TextView.class);
    target.mContactUs = Utils.findRequiredViewAsType(source, R.id.contactUs, "field 'mContactUs'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BookGenre target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mBookInput = null;
    target.mAuthorText = null;
    target.mTitleText = null;
    target.mContactUs = null;
  }
}
