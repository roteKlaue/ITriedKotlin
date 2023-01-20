package koejad20.bplaced.net.troll20.pojos;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import koejad20.bplaced.net.troll20.R;

public class Account implements Parcelable {
    private final long overdraft;
    private double AmountOfMoney;
    private final String iban;
    private final float interest;
    private final String label;
    private final int ImageId;
    private boolean isDebit = false;

    public Account(long overdraft, double AmountOfMoney, String iban, float interest, String label, int ImageId) {
        this.overdraft = overdraft;
        this.AmountOfMoney = AmountOfMoney;
        this.iban = iban;
        this.interest = interest;
        this.label = label;
        this.ImageId = ImageId;
    }

    protected Account(Parcel in) {
        overdraft = in.readLong();
        AmountOfMoney = in.readDouble();
        iban = in.readString();
        interest = in.readFloat();
        label = in.readString();
        ImageId = in.readInt();
        isDebit = in.readByte() != 0;
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

    public static Account makeGiroAccount(long overdraft, double amountOfMoney, String iban, float interest) {
        return new Account(overdraft, amountOfMoney, iban, interest, "GIRO", R.drawable.giro_account);
    }

    public static Account makeChildAccountAccount(double amountOfMoney, String iban, float interest, boolean isDebit) {
        final Account child = new Account(0, amountOfMoney, iban, interest, "STUDENT", R.drawable.student_account);
        child.setDebit(isDebit);
        return child;
    }

    public void setDebit(boolean debit) {
        isDebit = debit;
    }

    public void setAmountOfMoney(double AmountOfMoney) {
        this.AmountOfMoney = AmountOfMoney;
    }

    public long getOverdraft() {
        return overdraft;
    }

    public double getAmountOfMoney() {
        return AmountOfMoney;
    }

    public String getIban() {
        return iban;
    }

    public float getInterest() {
        return interest;
    }

    public String getLabel() {
        return label;
    }

    public int getImageId() {
        return ImageId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(overdraft);
        dest.writeDouble(AmountOfMoney);
        dest.writeString(iban);
        dest.writeFloat(interest);
        dest.writeString(label);
        dest.writeInt(ImageId);
        dest.writeByte((byte) (isDebit ? 1 : 0));
    }
}
