# Challenge2b

---------------------- Requirements -----------------------------

- Recycle mechanism (listview, recyclerview…)
	+ LayoutManager
	+ ItemDecoration
	+ ItemAnimator
- Gson options (attribs json different from attribs model class)
- Github



------------------------------------------------------------------------
---------------------- RecyclerView ------------------------------------

- Từ android 5.0
- Giống ListView nhưng linh hoạt hơn
- Chứa số lượng item lớn hơn
- Vuốt mượt hơn
- Hiệu ứng đẹp hơn
- Hỗ trợ đa dạng layout cho các list item

- Đối với Eclipse, cần lib android.support.v7.widget.RecyclerView
- Đối với Android Studio, thêm vào build.gradle (Module: app) dòng sau: 
	compile 'com.android.support:recyclerview-v7:23.0.0'

- Các subclass của RecyclerView
	+ Adapter: hiển thị dữ liệu của các item ra màn hình
	+ ViewHolder: cache các item để tránh redrawing
	+ LayoutManager: tính toán, đổi chỗ, tái chế các item
	+ ItemAnimator: thiết lập hiệu ứng khi add, remove ... các item
	+ ItemDecoration: trang trí bóng mờ, layout offsets

- class RecyclerView CHỈ quan tâm đến data set, còn các subclass của nó thì làm những việc khác

* Recycler adapter:
	+ notifyItemRemoved(position) sẽ có hiệu ứng animation
	+ notifyDataSetChanged() sẽ KHÔNG có hiệu ứng animation
	+ onCreateViewHolder giống giống hàm getView() của ArrayAdapter, nhưng return new ViewHolder(itemView)
	+ onBindViewHolder được gọi khi cần tạo view từ ViewHolder ở vị trí tương ứng trong list

- LayoutManager:
	+ LinearLayoutManager
	+ GridLayoutManager (extends LinearLayoutManager)
	+ StaggeredGridLayoutManager (so le) 
		https://www.youtube.com/watch?v=yC2EtzJ9E_A

- ItemAnimator: 
	+ DefaultItemAnimator
	+ Custom animator: wasabeef/recyclerview-animators, gabrielemariotti/RecyclerViewItemAnimators

- ItemDecoration: shadows, highlight, border ...

- RecyclerView và CardView là 2 layout mới, là 1 phần của Material Design mà Google tạo ra cho android 5.0

- Khi adapter thay đổi data set, adapter position sẽ được update trước rồi sau đó layout position sẽ được update cuối cùng, sau khi LayoutManager đã tính toán xong. Dùng layout position khi tương tác với các sự kiện của user hoặc khi viết RecyclerView.LayoutManager. Còn bình thường, khi code RecyclerView.Adapter thì dùng adapter position 

- Recycle mechanism: 
	+ Khi scroll ListView, RecyclerView, nếu một item bị out ra khỏi screen thì getView() sẽ chuyển item đó vào Recycler để có thể tái sử dụng.
	+ Khi đó, ListView, RecyclerView sẽ ko tạo item mới, mà nó sẽ dùng item đã có trong Recycler (convertView) để thay cho item vừa xuất hiện trong screen
	+ Item vừa xuất hiện này sẽ có các trạng thái mà item cũ trong Recycler đã có. Ví dụ: item cũ có CheckBox đã được check thì item mới xuất hiện cũng sẽ có CheckBox tương ứng đã 	được check
	+ Cơ chế này giúp tối ưu hiệu quả thực thi, vì ko tạo mới view mà reuse từ view cũ

----------------------- References ----------------------------------------------

- http://www.nguyenvanquan7826.com/2015/05/31/android-recyclerview-trong-android/

- https://thachnguyenit.wordpress.com/2015/03/05/android-recycleview-trong-android/

- http://blog.lovelyhq.com/creating-lists-with-recyclerview-in-android/
- http://blog.lovelyhq.com/creating-lists-with-recyclerview-in-android-part-2/

- https://developer.android.com/reference/android/support/v7/widget/RecyclerView.html

- http://stackoverflow.com/questions/11945563/how-listviews-recycling-mechanism-works



--------------------------------------------------------------------------------------------
------------------------ Git and Github ----------------------------------------------------

- Git là một mô hình Hệ thống quản lý phiên bản phân tán (Distributed Version Control System – DVCS)

- Sẽ có 1 server lưu trữ các version (những lần thay đổi) của src code. Những máy tính có quyền truy cập vào src code đó trên server, lấy version nào cần thiết, có thể thay đổi code rồi commit lại lên server

- Ko cần copy lại src code để backup thủ công

- Git lưu trữ version bằng cách tạo ra các snapshot chứ ko lưu cứng dữ liệu

- Giúp làm việc code theo nhóm đơn giản hơn - Làm việc ở bất cứ đâu (cần có internet)



- Githuh là một dịch vụ máy chủ repository hoạt động theo mô hình Git

--------------------------- Refererences ---------------------------------------

- http://thachpham.com/tools/git-gioi-thieu-serie-git-co-ban.html

- http://portal.daynhauhoc.com/p/huong-dan-dung-github-tren-windows-co-ban/



----------------------------------------------------------------------------------
---------------------------- Gson ------------------------------------------------

- Example:

private class SomeObject {
  @SerializedName("custom_naming") private final String someField;
  private final String someOtherField;

  public SomeObject(String a, String b) {
    this.someField = a;
    this.someOtherField = b;
  }
}

SomeObject someObject = new SomeObject("first", "second");
Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
String jsonRepresentation = gson.toJson(someObject);
System.out.println(jsonRepresentation);

======== OUTPUT ========
{"custom_naming":"first","SomeOtherField":"second"}

------------------------ References -------------------------------------------

- https://sites.google.com/site/gson/gson-user-guide#TOC-JSON-Field-Naming-Support