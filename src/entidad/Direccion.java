package entidad;

public class Direccion {
	
	private Localidad Loc;
	private String CalleYNum;
	
	public Localidad getLoc() {
		return Loc;
	}
	public void setLoc(Localidad loc) {
		Loc = loc;
	}
	public String getCalleYNum() {
		return CalleYNum;
	}
	public void setCalleYNum(String calleYNum) {
		CalleYNum = calleYNum;
	}
	
	@Override
	public String toString() {
		return "Direccion [Loc=" + Loc + ", CalleYNum=" + CalleYNum + "]";
	}
	
	public Direccion(Localidad loc, String calleYNum) {
		Loc = loc;
		CalleYNum = calleYNum;
	}
	
	public Direccion() {}
	
}
