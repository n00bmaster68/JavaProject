import java.util.*;

public class HoaDon
{
	String mahd;
	String soBan;
	String nv;
	String ngay;
	Double tongTien;
	String tinhTrang;
	ArrayList<CTHoaDon> ct = new ArrayList<CTHoaDon>(); 

	public HoaDon(){}
	public HoaDon (String mahd, String soBan, String nv, String ngay, Double tongTien, String tinhTrang)
	{
		this.mahd = mahd;
		this.soBan = soBan;
		this.nv = nv;
		this.ngay = ngay;
		this.tongTien = tongTien;
		this.tinhTrang = tinhTrang;
	}
}