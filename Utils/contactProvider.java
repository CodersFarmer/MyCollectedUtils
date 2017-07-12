package yqz.heima.test2;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;

//import com.itheima.yqzmobliesafe.bean.contactBean;

public class contactProvider {
//获取所有联系人
	public static List<contactBean> getAllContacts(Context context) {
		List<contactBean> list = new ArrayList<contactBean>();
		// 数据查询--》系统的联系人
		ContentResolver cr = context.getContentResolver();
		// 通过号码查询
		Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;// 要查询数据对应的uri地址
		String[] projection = new String[] {
				ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,// 名称
				ContactsContract.CommonDataKinds.Phone.NUMBER,// 号码
				ContactsContract.CommonDataKinds.Phone.CONTACT_ID // 联系人的id
		};// 要查询的列
		String selection = null;// 查询的条件
		String[] selectionArgs = null;// 查询条件对应的参数
		String sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
				+ " desc";// 查询的排序

		Cursor cursor = cr.query(uri, projection, selection, selectionArgs,
				sortOrder);
		if (cursor != null) {
			while (cursor.moveToNext()) {
				String name = cursor.getString(0);
				String number = cursor.getString(1);
				long contactId = cursor.getLong(2);

				contactBean bean = new contactBean();
				bean.name = name;
				bean.number = number;
				bean.contactid = contactId;

				list.add(bean);
			}
			cursor.close();
		}
		return list;
	}
	//获取联系人的图片
	public static Bitmap getContactPhoto(Context context, long contactId) {
		ContentResolver cr = context.getContentResolver();

		// content://contacts //所有联系人
		// content://contacts/1 //具体联系人

		// ContactsContract.Contacts.CONTENT_URI;//所有联系人的uri
		Uri contactUri = Uri.withAppendedPath(
				ContactsContract.Contacts.CONTENT_URI,
				String.valueOf(contactId));
		InputStream stream = ContactsContract.Contacts
				.openContactPhotoInputStream(cr, contactUri);

		// 将流转换为bitmap
		return BitmapFactory.decodeStream(stream);
	}

}
