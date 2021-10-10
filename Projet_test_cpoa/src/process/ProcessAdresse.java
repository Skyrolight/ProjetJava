package process;

import modele.Adresse;

public class ProcessAdresse {

	public String normalizePays(String pays) {
		if (pays.trim().length()!=0) {
		
		String pays1 = pays.trim().toLowerCase();
	
			switch (pays1) {
				case "letzebuerg":
					return "Luxembourg";
				case "belgium":
					return "Belgique";
				case "switzerland":
				case "schweiz":
					return "Suisse";
				default:
					return pays1;
			}
		}
		else {
			throw new IllegalArgumentException("Pays vide interdit !");
			}
	}
	
	public String normalizeVille(String ville) {
		    if (ville.trim().length()!=0) {
		        final String separateur = " ";
		        final String separateur2 = "-";
		        String prepo[] = {"le", "lès", "sous", "sur", "�", "aux"};
		        String VilleFin = ville.toLowerCase();

		        if (VilleFin.contains(separateur)) {
		            String VilleTri[] = VilleFin.split(separateur);
		        for (int i = 0; i < VilleTri.length; i++){
		            VilleTri[i] = VilleTri[i].trim();
		        }
		        VilleFin = "";
		        for (int i = 0; i < VilleTri.length; i++){ 
		            if (VilleTri.length == i+1) {
		                String str = VilleTri[i];
		                String output = str.substring(0, 1).toUpperCase() + str.substring(1);
		                VilleFin = VilleFin + output;
		            }else{
		                if (VilleTri[i].compareTo("le") == 0){
		                    for (int k = 0; k < prepo.length; k++){
		                        if (VilleTri[i].compareTo(prepo[k]) == 0) {
		                            VilleFin = VilleFin + prepo[k] + "-";
		                        }
		                    }
		                }

		                else if (VilleTri[i].compareTo("lès") == 0){
		                    for (int k = 0; k < prepo.length; k++){
		                        if (VilleTri[i].compareTo(prepo[k]) == 0) {
		                            VilleFin = VilleFin + prepo[k] + "-";
		                        }
		                    }
		                }

		                else if (VilleTri[i].compareTo("sous") == 0){
		                    for (int k = 0; k < prepo.length; k++){
		                        if (VilleTri[i].compareTo(prepo[k]) == 0) {
		                            VilleFin = VilleFin + prepo[k] + "-";
		                        }
		                    }
		                }

		                else if (VilleTri[i].compareTo("sur") == 0){
		                    for (int k = 0; k < prepo.length; k++){
		                        if (VilleTri[i].compareTo(prepo[k]) == 0) {
		                            VilleFin = VilleFin + prepo[k] + "-";
		                        }
		                    }
		                }

		                else if (VilleTri[i].compareTo("à") == 0){
		                    for (int k = 0; k < prepo.length; k++){
		                        if (VilleTri[i].compareTo(prepo[k]) == 0) {
		                            VilleFin = VilleFin + prepo[k] + "-";
		                        }
		                    }
		                }

		                else if (VilleTri[i].compareTo("aux") == 0){
		                    for (int k = 0; k < prepo.length; k++){
		                        if (VilleTri[i].compareTo(prepo[k]) == 0) {
		                            VilleFin = VilleFin + prepo[k] + "-";
		                        }
		                    }
		                }
		                
		                else if (VilleTri[i].compareTo("st") == 0) {
		                    VilleFin = VilleFin + "Saint" + "-";
		                }
		                else if (VilleTri[i].compareTo("ste") == 0) {
		                    VilleFin = VilleFin + "Sainte" + "-";
		                }
		                else {
		                    String str = VilleTri[i];
		                    String output = "";
		                    
		                    output = str.substring(0, 1).toUpperCase() + str.substring(1);
		                    VilleFin = VilleFin + output + "-";
		                }
		            }
		        }
		    return VilleFin; 
		} else if (VilleFin.contains(separateur2)) {
		    String VilleTri[] = VilleFin.split(separateur2);
		for (int i = 0; i < VilleTri.length; i++){
		    VilleTri[i] = VilleTri[i].trim();
		}
		VilleFin = "";
		for (int i = 0; i < VilleTri.length; i++){ 
		    if (VilleTri.length == i+1) {
		        String str = VilleTri[i];
		        String output = str.substring(0, 1).toUpperCase() + str.substring(1);
		        VilleFin = VilleFin + output;
		    }else{
		        if (VilleTri[i].compareTo("le") == 0){
		            for (int k = 0; k < prepo.length; k++){
		                if (VilleTri[i].compareTo(prepo[k]) == 0) {
		                    VilleFin = VilleFin + prepo[k] + "-";
		                }
		            }
		        }

		        else if (VilleTri[i].compareTo("lès") == 0){
		            for (int k = 0; k < prepo.length; k++){
		                if (VilleTri[i].compareTo(prepo[k]) == 0) {
		                    VilleFin = VilleFin + prepo[k] + "-";
		                }
		            }
		        }

		        else if (VilleTri[i].compareTo("sous") == 0){
		            for (int k = 0; k < prepo.length; k++){
		                if (VilleTri[i].compareTo(prepo[k]) == 0) {
		                    VilleFin = VilleFin + prepo[k] + "-";
		                }
		            }
		        }

		        else if (VilleTri[i].compareTo("sur") == 0){
		            for (int k = 0; k < prepo.length; k++){
		                if (VilleTri[i].compareTo(prepo[k]) == 0) {
		                    VilleFin = VilleFin + prepo[k] + "-";
		                }
		            }
		        }

		        else if (VilleTri[i].compareTo("à") == 0){
		            for (int k = 0; k < prepo.length; k++){
		                if (VilleTri[i].compareTo(prepo[k]) == 0) {
		                    VilleFin = VilleFin + prepo[k] + "-";
		                }
		            }
		        }

		        else if (VilleTri[i].compareTo("aux") == 0){
		            for (int k = 0; k < prepo.length; k++){
		                if (VilleTri[i].compareTo(prepo[k]) == 0) {
		                    VilleFin = VilleFin + prepo[k] + "-";
		                }
		            }
		        }
		        
		        else if (VilleTri[i].compareTo("st") == 0) {
		            VilleFin = VilleFin + "Saint" + "-";
		        }
		        else if (VilleTri[i].compareTo("ste") == 0) {
		            VilleFin = VilleFin + "Sainte" + "-";
		        }
		        else {
		            String str = VilleTri[i];
		            String output = "";
		            
		            output = str.substring(0, 1).toUpperCase() + str.substring(1);
		            VilleFin = VilleFin + output + "-";
		                }
		            }
		        }
		        return VilleFin; 
		            }
		        }
		    else {
				throw new IllegalArgumentException("Ville vide interdit !");
				}
			return ville;
		        
	
	}
	
	public String normalizeCode_postal(String code_postal) {

		String text = "";
		
	        if (code_postal.length() != 0) {
	            text = code_postal.replaceAll("\\D+","");
	            
	            while (text.length() != 5) {
	                text = "0" + text;
	                
	            }
	            return text;
	        }
	        else {
				throw new IllegalArgumentException("Code postal vide interdit !");
				}
	    }

		
	public String normalizeVoie(String voie) {
		if (voie.trim().length()!=0) {
			String abreviation[] = {"boul","boul.","av.","faub.","fg","pl."} ;
			String tab[] = voie.split(" ");
			
			for(int j=0;j<abreviation.length;j++) {
		
				if(tab[0].equals(tab[j])) {
					
					String v = change(tab[0].toLowerCase());
					
					String restVoie = "";
					for(int i=1;i<tab.length;i++) {
						restVoie = restVoie + " " + tab[i];
					}
					String res = v + restVoie;
					return res;
			
				};
			}
 			return voie;
			
		}
		else {
			throw new IllegalArgumentException("Voie vide interdit !");
			}
	}

	private String change(String abrev) {
		switch (abrev) {
		case "boul","boul.","bd": {
			return "boulevard";
		}
		case "av.": {
			return "avenue";
		}
		case "faub","fg": {
			return "faubourg";
		}
		default:
			return abrev;
		}
	}

	public int normalizeNo_rue(int no_rue) {
		return no_rue;
	}

	

}














