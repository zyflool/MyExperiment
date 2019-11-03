package com.example.criminalintent;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CrimeFragment extends Fragment {

    private static final String ARG_CRIME_ID = "crime_id";
    private static final String DIALOG_DATE = "DialogDate";

    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_CONTACT = 1;
    private static final int REQUEST_PHOTO = 2;

    private Crime mCrime;
    private File mPhotoFile;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    private Button mSuspectButton;
    private Button mReportButton;
    private ImageButton mPhotoButton;
    private ImageView mPhotoView;

    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
        mPhotoFile = CrimeLab.get(getActivity()).getPhotoFile(mCrime);//获取照片文件位置
    }

    @Override
    public void onPause() {
        super.onPause();

        CrimeLab.get(getActivity())
                .updateCrime(mCrime);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);

        mTitleField = v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mDateButton = v.findViewById(R.id.crime_date);
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getDate());

                //设置目标fragment类似于activity间的关联,可将 CrimeFragment 设置成 DatePickerFragment 的目标fragment。
                //这样,在 CrimeFragment 和 DatePickerFragment 被销毁并重建后,操作系统会重新关联它们。
                //调用以下 Fragment 方法可建立这种关联:  public void setTargetFragment(Fragment fragment, int requestCode)
                //该方法有两个参数:目标fragment以及类似于传入 startActivityForResult(...) 方法的请求代码。
                // 需要时,目标fragment使用请求代码确认是哪个fragment在回传数据。
                //目标fragment和请求代码由 FragmentManager 负责跟踪管理,我们可调用fragment(设置目标fragment的fragment)的 getTargetFragment() 方法和 getTargetRequestCode() 方法获取它们。
                //在CrimeFragment.java中,创建请求代码常量,然后将 CrimeFragment 设为 DatePickerFragment 实例的目标fragment。
                dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        mSolvedCheckBox = v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });

        mReportButton = v.findViewById(R.id.crime_report);
        mReportButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                //接受字符串参数的 Intent 构造方法
                i.setType("text/plain");//操作涉及的数据类型,指的是MIME形式的数据类型,如text/html或audio/mpeg3。
                i.putExtra(Intent.EXTRA_TEXT, getCrimeReport());
                i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.crime_report_subject));
                i = Intent.createChooser(i, getString(R.string.send_report));//创建每次都显示的activity选择器
                startActivity(i);
            }
        });

        final Intent pickContact = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        //操作为 Intent.ACTION_PICK 。联系人数据获取位置为 ContactsContract.Contacts.CONTENT_URI
        mSuspectButton = v.findViewById(R.id.crime_suspect);
        mSuspectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(pickContact, REQUEST_CONTACT);
            }
        });

        if ( mCrime.getSuspect() != null ) {
            mSuspectButton.setText(mCrime.getSuspect());
        }

        /**
         * Android设备上安装了哪些组件以及包括哪些activity, PackageManager 类全都知道。
         * 调用 resolveActivity(Intent, int) 方法,可以找到匹配给定 Intent 任务的activity。
         */
        PackageManager packageManager = getActivity().getPackageManager();
        if ( packageManager.resolveActivity(pickContact, PackageManager.MATCH_DEFAULT_ONLY) == null ) {
            //flag标志 MATCH_DEFAULT_ONLY 限定只搜索带 CATEGORY_DEFAULT 标志的activity。这和 startActivity(Intent) 方法类似。
            //如果搜到目标,它会返回 ResolveInfo 告诉我们找到了哪个activity。
            mSuspectButton.setEnabled(false);
            //如果找不到的话,必须禁用嫌疑人按钮,否则应用就会崩溃。
        }

        mPhotoButton = v.findViewById(R.id.crime_camera);
        mPhotoView = v.findViewById(R.id.crime_photo);
        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //MediaStore.ACTION_IMAGE_CAPTURE打开相机应用,默认只能拍摄缩略图这样的低分辨率照片,
        // 而且照片会保存在 onActivityResult(...) 返回的 Intent 对象里。

        boolean canTakePhoto = (mPhotoFile != null) &&
                (captureImage.resolveActivity(packageManager) != null);
        //检查设备上是否安装有相机应用,以及是否有地方存储照片
        mPhotoButton.setEnabled(canTakePhoto);

        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FileProvider.getUriForFile()把本地文件路径转换为相机能看见的 Uri 形式
                Uri uri = FileProvider.getUriForFile(getActivity(),
                        "com.example.criminalintent.fileprovider", mPhotoFile);
                captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);//将指向存储路径的Uri指向FileProvider提供的位置
                //                    指向存储路径的Uri

                List<ResolveInfo> cameraActivities = getActivity()
                        .getPackageManager()
                        .queryIntentActivities(captureImage, PackageManager.MATCH_DEFAULT_ONLY);

                for ( ResolveInfo activity : cameraActivities ) {
                    //要实际写入文件,还需要给相机应用权限。
                    // 为了授权,我们授予 FLAG_GRANT_WRITE_URI_PERMISSION
                    // 给所有 cameraImage intent的目标activity,以此允许它们在 Uri 指定的位置写文件。
                    getActivity().grantUriPermission(activity.activityInfo.packageName,
                            uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }
                startActivityForResult(captureImage, REQUEST_PHOTO);
            }
        });

        //把 Bitmap 载入 ImageView
        updatePhotoView();

        return v;
    }

    //覆盖 onActivityResult(...) 方法,从extra中获取日期数据,设置对应 Crime 的记录日期,然后刷新日期按钮的显示。
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if ( resultCode != Activity.RESULT_OK ) {
            return ;
        }
        if ( requestCode == REQUEST_DATE ) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setDate(date);
            updateDate();
        } else if ( requestCode == REQUEST_CONTACT && data != null) {
            Uri contactUri = data.getData();
            //Android提供了一个深度定制的API用于处理联系人信息: ContentProvider 类。
            // 该类的实例封装了联系人数据库并提供给其他应用使用。
            // 我们可以通过 ContentResolver 访问 ContentProvider 。
            //developer.android.com/guide/topics/providers/contacts-provider.html
            String[] queryFields = new String[] {ContactsContract.Contacts.DISPLAY_NAME};
            Cursor c = getActivity().getContentResolver()
                    .query(contactUri, queryFields, null, null, null);

            try {
                if ( c.getCount() == 0 ) {
                    return ;
                }
                c.moveToFirst();
                String suspect = c.getString(0);
                mCrime.setSuspect(suspect);
                mSuspectButton.setText(suspect);
            } finally {
                c.close();
            }
        } else if ( requestCode == REQUEST_PHOTO ) {
            Uri uri = FileProvider.getUriForFile(getActivity(),
                    "com.example.criminalintent.fileprovider",
                    mPhotoFile);

            getActivity().revokeUriPermission(uri,
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

            //把 Bitmap 载入 ImageView
            updatePhotoView();
        }
    }

    private void updateDate() {
        mDateButton.setText(mCrime.getDate().toString());
    }

    //创建四段字符串信息,并返回拼接完整的消息
    private String getCrimeReport() {
        String solvedString = null;
        if ( mCrime.isSolved() ) {
            solvedString = getString(R.string.crime_report_solved);
        } else {
            solvedString = getString(R.string.crime_report_unsolved);
        }

        String dateFormat = "EEE, MMM dd";
        String dateString = DateFormat.format(dateFormat, mCrime.getDate()).toString();

        String suspect = mCrime.getSuspect();
        if (suspect == null) {
            suspect = getString(R.string.crime_report_no_suspect);
        } else {
            suspect = getString(R.string.crime_report_suspect, suspect);
        }

        String report = getString(R.string.crime_report,
                mCrime.getTitle(), dateString, solvedString, suspect);

        return report;
    }

    //把 Bitmap 载入 ImageView
    private void updatePhotoView ( ) {
        if ( mPhotoFile == null || !mPhotoFile.exists() ) {
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaleBitmap(
                    mPhotoFile.getPath(), getActivity());
            mPhotoView.setImageBitmap(bitmap);
        }
    }
}
