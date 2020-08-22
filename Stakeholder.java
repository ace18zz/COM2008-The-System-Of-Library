public class Stakeholder extends Database{
	protected String title;
	protected String forename;
	protected String surname;
	protected String affiliation;
	protected String email;
	protected String password;
	public Stakeholder(String titleS,String forenameS, String surnameS, String affiliationS, String emailS,String passwordS) {
		this.title = titleS;
		this.forename = forenameS;
		this.surname = surnameS;
		this.affiliation = affiliationS;
		this.email = emailS;
		this.password = passwordS;
	}
	public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getForename() {
        return forename;
    }
    public void setForename(String forename) {
        this.forename = forename;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getAffiliation() {
        return affiliation;
    }
    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void getProperties() {
    	System.out.println(title +", "+forename+", "+surname+", "+affiliation+", "+email+", "+password);
    }
    
	public String toString() {
		return (title +", "+forename+", "+surname+", "+affiliation+", "+email);
	}
	
    /*public int getTeamId() {
        return teamId;
    }
    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }*/
}