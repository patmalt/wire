// Code generated by Wire protocol buffer compiler, do not edit.
// Source: squareup.proto3.BuyOneGetOnePromotion in pizza.proto
package squareup.proto3;

import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.Syntax;
import com.squareup.wire.WireField;
import com.squareup.wire.internal.Internal;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import okio.ByteString;

public final class BuyOneGetOnePromotion extends Message<BuyOneGetOnePromotion, BuyOneGetOnePromotion.Builder> {
  public static final ProtoAdapter<BuyOneGetOnePromotion> ADAPTER = ProtoAdapter.newMessageAdapter(BuyOneGetOnePromotion.class, "type.googleapis.com/squareup.proto3.BuyOneGetOnePromotion", Syntax.PROTO_3);

  private static final long serialVersionUID = 0L;

  @WireField(
      tag = 1,
      adapter = "com.squareup.wire.ProtoAdapter#STRING",
      label = WireField.Label.OMIT_IDENTITY
  )
  public final String coupon;

  public BuyOneGetOnePromotion(String coupon) {
    this(coupon, ByteString.EMPTY);
  }

  public BuyOneGetOnePromotion(String coupon, ByteString unknownFields) {
    super(ADAPTER, unknownFields);
    if (coupon == null) {
      throw new IllegalArgumentException("coupon == null");
    }
    this.coupon = coupon;
  }

  @Override
  public Builder newBuilder() {
    Builder builder = new Builder();
    builder.coupon = coupon;
    builder.addUnknownFields(unknownFields());
    return builder;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) return true;
    if (!(other instanceof BuyOneGetOnePromotion)) return false;
    BuyOneGetOnePromotion o = (BuyOneGetOnePromotion) other;
    return unknownFields().equals(o.unknownFields())
        && Internal.equals(coupon, o.coupon);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode;
    if (result == 0) {
      result = unknownFields().hashCode();
      result = result * 37 + (coupon != null ? coupon.hashCode() : 0);
      super.hashCode = result;
    }
    return result;
  }

  public static final class Builder extends Message.Builder<BuyOneGetOnePromotion, Builder> {
    public String coupon;

    public Builder() {
      coupon = "";
    }

    public Builder coupon(String coupon) {
      this.coupon = coupon;
      return this;
    }

    @Override
    public BuyOneGetOnePromotion build() {
      return new BuyOneGetOnePromotion(coupon, super.buildUnknownFields());
    }
  }
}
