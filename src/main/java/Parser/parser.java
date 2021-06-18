package Parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.prowidesoftware.swift.model.mt.mt9xx.MT940;

import dao.FileDAO;
import dao.UserDAO;

public class parser {
	String mt3;
	ArrayList<String> files = new ArrayList<String>();
	ArrayList<LinkedHashMap<String, ArrayList<String>>> content = new ArrayList<LinkedHashMap<String, ArrayList<String>>>();
	public HashMap<Integer, String> map1 = new HashMap<Integer, String>();
	public HashMap<Integer, String> map2 = new HashMap<Integer, String>();

	public parser(String mt940) {
		this.mt3 = mt940;
	}

	public String getField(int index1, int index2, String s, int i) {
		return s.substring(index1 + i, index2);
	}

	public int getContentSize() {
		return content.size();
	}

	public ArrayList<LinkedHashMap<String, ArrayList<String>>> getContent() {
		return content;
	}

	public void parseFile() {
		int n20 = mt3.indexOf(":20:");
		while (n20 != -1) {
			if (mt3.indexOf(":20:", n20 + 1) != -1) {
				files.add(getField(n20, mt3.indexOf(":20:", n20 + 1), mt3, 4));
			} else {
				files.add(getField(n20, mt3.length(), mt3, 4));
			}
			n20 = mt3.indexOf(":20:", n20 + 1);
		}
		for (int i = 0; i < files.size(); i++) {
			LinkedHashMap<String, ArrayList<String>> map = new LinkedHashMap<String, ArrayList<String>>();
			String mt = files.get(i);
			n20 = mt.indexOf(":20:");
			int n21 = -2;
			int n25 = mt.indexOf(":25:");
			if (mt.indexOf(":21:") != -1) {
				n21 = mt.indexOf(":21:");
				String refNumber = getField(0, n21, mt, 0);
				map.put(":20:", new ArrayList<String>());
				map.get(":20:").add(refNumber);
				String relatedRef = getField(n20, n21, mt, 4);
				map.put(":21:", new ArrayList<String>());
				map.get(":21:").add(relatedRef);

			} else {
				String refNumber = getField(0, n25, mt, 0);
				map.put(":20:", new ArrayList<String>());
				map.get(":20:").add(refNumber);
			}
			int n28 = mt.indexOf(":28C:");
			int len28 = 5;
			String key28 = ":28C:";
			if (n28 == -1) {
				n28 = mt.indexOf(":28:");
				len28 = 4;
				key28 = ":28:";
			}
			String iban = getField(n25, n28, mt, 4);
			map.put(":25:", new ArrayList<String>());
			map.get(":25:").add(iban);

			int n60 = mt.indexOf(":60F:");
			int len60 = 5;
			String key60 = ":60F:";
			if (n60 == -1) {
				len60 = 4;
				key60 = ":60M:";
				n60 = mt.indexOf(":60M:");
			}

			String openingNumber = getField(n28, n60, mt, len60);
			map.put(key28, new ArrayList<String>());
			map.get(key28).add(openingNumber);
			map.put(key60, new ArrayList<String>());
			map.put(":61:", new ArrayList<String>());
			int n61 = 0;
			int n86 = 0;
			int cnt = 0;
			boolean is61 = false;
			while (mt.indexOf(":61:", n61 + 1) != -1) {
				/**
				 * String statementLine = null; n61 = mt.indexOf(":61:", n61 + 1); // int
				 * nextTag = n86; // if(mt.indexOf(":61:", n61 + 1) < mt.indexOf(":86:", n86 +
				 * 1)) { // nextTag = n1 // } String extraInfo = null; if (n86 != 0 && n61 >
				 * n86) { extraInfo = getField(n86, n61, mt, 4); n86 = mt.indexOf(":86:", n86 +
				 * 1); } if (mt.indexOf(":61:", n61 + 1) > mt.indexOf(":86:", n86 + 1)) { n86 =
				 * mt.indexOf(":86:", n86 + 1); }
				 * 
				 * if (mt.indexOf(":61:", n61 + 1) > n86) { statementLine = getField(n61, n86,
				 * mt, 4); } else if (mt.indexOf(":86:", n86 + 1) != -1) { statementLine =
				 * getField(n61, mt.indexOf(":61:", n61 + 1), mt, 4); }
				 * 
				 * ; if (cnt == 0) { map.get(key60).add(getField(n60, n61, mt, 5));
				 * map.put(":61:", new ArrayList<String>()); map.put(":86:", new
				 * ArrayList<String>()); } map.get(":61:").add(statementLine); if (extraInfo !=
				 * null) { map.get(":86:").add(extraInfo); } cnt++;
				 **/
				n61 = mt.indexOf(":61:", n61 + 1);

			}
			int n62 = mt.indexOf(":62F:");
			String key62 = ":62F:";
			if (n62 == -1) {
				n62 = mt.indexOf(":62M:");
				key62 = ":62M:";
			}
			if (map.get(key60).size() == 0) {
				map.get(key60).add(getField(n60, n62, mt, 5));
			}
			if (map.containsKey(":86:")) {
				map.get(":86:").add(getField(n86, n62, mt, 4));
			}
			map.put(key62, new ArrayList<String>());
			int n64 = mt.indexOf(":64:");
			int n65 = mt.indexOf(":65:");
			n86 = mt.indexOf(":86:", n86 + 1);
			int end = mt.length() - 1;
			if (mt.contains("-}") || mt.charAt(mt.length() - 1) == '-') {
				end = mt.indexOf("-}") - 1;
				if (end == -2) {
					end = mt.length() - 2;
				}
				System.out.println(mt.charAt(mt.length() - 1));
			}
			// System.out.println(mt.charAt(mt.length()-3));
			if (n64 != -1) {
				map.get(key62).add(getField(n62, n64, mt, 5));
				map.put(":64:", new ArrayList<String>());
				if (n65 != -1) {
					map.get(":64:").add(getField(n64, n65, mt, 4));
					map.put(":65:", new ArrayList<String>());
					if (n86 != -1) {
						map.get(":65:").add(getField(n65, n86, mt, 4));
						if (!map.containsKey(":86:")) {
							map.put(":86:", new ArrayList<String>());
						}
						map.get(":86:").add(getField(n86, mt.indexOf("\n", n86), mt, 4));
					} else {
						map.get(":65:").add(getField(n65, mt.indexOf("\n", n65), mt, 4));
					}
				} else {
					if (n86 != -1) {
						map.get(":64:").add(getField(n64, n86, mt, 4));
						if (!map.containsKey(":86:")) {
							map.put(":86:", new ArrayList<String>());
						}
						map.get(":86:").add(getField(n86, mt.indexOf("\n", n86), mt, 4));
					} else {
						map.get(":64:").add(getField(n64, mt.indexOf("\n", n64), mt, 4));
					}
				}
			} else if (n65 != -1) {
				map.get(key62).add(getField(n62, n65, mt, 5));
				map.put(":65:", new ArrayList<String>());
				if (n86 != -1) {
					map.get(":65:").add(getField(n65, n86, mt, 4));
					if (!map.containsKey(":86:")) {
						map.put(":86:", new ArrayList<String>());
					}
					map.get(":86:").add(getField(n86, mt.indexOf("\n", n86), mt, 4));
				} else {
					map.get(":65:").add(getField(n65, mt.indexOf("\n", n65), mt, 4));
				}
			} else {
				if (n86 != -1) {
					map.get(key62).add(getField(n62, n86, mt, 5));
					if (!map.containsKey(":86:")) {
						map.put(":86:", new ArrayList<String>());
					}
					map.get(":86:").add(getField(n86, mt.indexOf("\n", n86), mt, 4));
				} else if (map.containsKey(":62F:")) {
					map.get(key62).add(getField(n62, mt.indexOf("\n", n62), mt, 5));
				}
			}
			content.add(map);
			for (String key : map.keySet()) {
				for (int j = 0; j < map.get(key).size(); j++) {
					if (map.get(key).get(j) != null) {
						if (map.get(key).get(j).contains(key)) {
							map.get(key).set(j, map.get(key).get(j).substring(0, map.get(key).get(j).indexOf(key)));
						}
					}
				}
			}
		}
	}

	public String get20field(int index) {
		if (index >= content.size()) {
			return "Error";
		} else {
			return content.get(index).get(":20:").get(0);
		}
	}

	public String get21Field(int index) {
		if (index >= content.size()) {
			return "Error";
		} else {
			if (content.get(index).containsKey(":21:")) {
				return content.get(index).get(":21:").get(0);
			} else {
				return "No info available";
			}
		}
	}

	public String get25field(int index) {
		if (index >= content.size()) {
			return "Error";
		} else {
			return content.get(index).get(":25:").get(0);
		}
	}

	public String get28field(int index) {
		if (index >= content.size()) {
			return "Error";
		} else {
			if (content.get(index).containsKey(":28:")) {
				return content.get(index).get(":28:").get(0);
			} else {
				return content.get(index).get(":28C:").get(0);
			}

		}
	}

	public LinkedHashMap<String, String> get60field(int index) {
		LinkedHashMap<String, String> field60 = new LinkedHashMap<String, String>();
		String field;
		if (index >= content.size()) {
			return null;
		} else if (content.get(index).containsKey(":60F:")) {
			field = content.get(index).get(":60F:").get(0);
			field60.put("Last Statement Date",
					field.substring(1, 3) + "-" + field.substring(3, 5) + "-" + field.substring(5, 7));
		} else {
			field = content.get(index).get(":60M:").get(0);
			field60.put("Current Statement Date",
					field.substring(1, 3) + "-" + field.substring(3, 5) + "-" + field.substring(5, 7));
		}
		field60.put("D/C", String.valueOf(field.charAt(0)));
		field60.put("Currency", field.substring(7, 10));
		field60.put("Ammount", field.substring(10));
		return field60;
	}

	public LinkedHashMap<String, String> get61field(int index, int line) {
		LinkedHashMap<String, String> field61 = new LinkedHashMap<String, String>();
		int until = 0;
		String field;
		String temp;
		if (line >= content.size() || line >= content.get(index).get(":61:").size()) {
			return null;
		} else if (content.get(index).containsKey(":61:")) {
			field = content.get(index).get(":61:").get(line);
			field61.put("Value Date",
					field.substring(0, 2) + "-" + field.substring(2, 4) + "-" + field.substring(4, 6));
			field61.put("Entry Date", field.substring(6, 8) + "-" + field.substring(8, 10));
			field61.put("Credit/Debit", String.valueOf(field.charAt(10)));
			temp = field.substring(11);
			for (int i = 0; i <= temp.length() - 1; i++) {
				if ((int) temp.charAt(i) >= 65 && (int) temp.charAt(i) <= 90) {
					until = temp.indexOf(temp.charAt(i));
					break;
				}
			}
			until = until + 11;
			if (String.valueOf(field.charAt(10)).equals("D")) {
				field61.put("Transaction Amount", "-" + field.substring(11, until));
			} else if (String.valueOf(field.charAt(10)).equals("C")) {
				field61.put("Transaction Amount", "+" + field.substring(11, until));
			}
			if (field.contains("//")) {
				until = field.indexOf("//") + 2;
				field61.put("Customer Reference", field.substring(until, until + 16));
			}

		}
		return field61;
	}

	public void parseLine86(String msg) {
		String line86[] = null;

		if (msg.contains("/")) {
			// delimiter = "/";
			line86 = msg.split("/");
			// nameDelimiter = "REMI";
		} else {
			String firstSplit[] = msg.split("  ");
			line86 = firstSplit[0].split(" ");
			// delimiter = " ";
			// nameDelimiter = " ";
		}
	}

	public LinkedHashMap<String, String> get86field(int index, int line) {
		LinkedHashMap<String, String> field86 = new LinkedHashMap<String, String>();
		String field;
		String temp;
		int until = 0;
		List<Integer> indexIBAN = new ArrayList<>();
		if (index >= content.size() || line >= content.get(index).get(":86:").size()
				|| content.get(index).get(":86:").get(line).contains("SUM")) {
			return null;
		} else if (content.get(index).containsKey(":86:")) {
			field = content.get(index).get(":86:").get(line);
			for (int i = field.indexOf("NL"); i >= 0; i = field.indexOf("NL", i + 1)) {
				indexIBAN.add(i);
			}
			if (indexIBAN.size() == 1) {
				for (int i = 0; i < indexIBAN.size(); i++) {
					field86.put("TransactionIBAN", field.substring(indexIBAN.get(i), indexIBAN.get(i) + 18));
				}
			} else if (indexIBAN.size() > 1) {
				field86.put("TransactionIBAN", field.substring(indexIBAN.get(0), indexIBAN.get(0) + 18));
				field86.put("IncasantID", field.substring(indexIBAN.get(1), indexIBAN.get(1) + 19));
			}
			if (content.get(index).get(":86:").get(line).contains("Factuur")) {
				int start = field.indexOf("Factuur");
				field86.put("Omschrijving-1", field.substring(start));
			}
			if (indexIBAN.size() >= 1) {
				int fromIban = indexIBAN.get(0) + 19;
				temp = field.substring(fromIban);
				for (int i = 0; i <= temp.length() - 1; i++) {
					if ((int) temp.charAt(i) >= 48 && (int) temp.charAt(i) <= 57) {
						until = temp.indexOf(temp.charAt(i));
						break;
					}
				}
				temp = field.substring(fromIban, fromIban + until);
				if (temp.contains("/")) {
					String[] arrOfStr = temp.split("/");
					temp = arrOfStr[1];
				}
				field86.put("Naam tegenpartij", temp);
			}
		}
		return field86;
	}

	public LinkedHashMap<String, String> get62Ffield(int index, int line) {
		LinkedHashMap<String, String> field62F = new LinkedHashMap<String, String>();
		String field;
		if (index >= content.size()) {
			return null;
		} else if (content.get(index).containsKey(":62F:")) {
			field = content.get(index).get(":62F:").get(line);
			field62F.put("Credit/Debit", field.substring(0, 1));
			field62F.put("Balance Date",
					field.substring(1, 3) + "-" + field.substring(3, 5) + "-" + field.substring(5, 7));
			field62F.put("Currency", field.substring(7, 10));
			field62F.put("Ammount", field.substring(10));
		}
		return field62F;
	}

	public LinkedHashMap<String, String> get64field(int index, int line) {
		LinkedHashMap<String, String> field64 = new LinkedHashMap<String, String>();
		String field;
		if (index >= content.size() || line >= content.get(index).get(":64:").size()) {
			return null;
		} else if (content.get(index).containsKey(":64:")) {
			field = content.get(index).get(":64:").get(line);
			field64.put("Credit/Debit", field.substring(0, 1));
			field64.put("Balance Date",
					field.substring(1, 3) + "-" + field.substring(3, 5) + "-" + field.substring(5, 7));
			field64.put("Currency", field.substring(7, 10));
			field64.put("Ammount", field.substring(10));
		}
		return field64;
	}

	public HashMap<String, String> get65field(int index, int line) {
		HashMap<String, String> field65 = new HashMap<String, String>();
		String field;
		if (index >= content.size() || line >= content.get(index).get(":65:").size()) {
			return null;
		} else if (content.get(index).containsKey(":65:")) {
			field = content.get(index).get(":65:").get(line);
			field65.put("Credit/Debit", field.substring(0, 1));
			field65.put("Balance Date",
					field.substring(1, 3) + "-" + field.substring(3, 5) + "-" + field.substring(5, 7));
			field65.put("Currency", field.substring(7, 10));
			field65.put("Ammount", field.substring(10));
		}
		return field65;
	}

	public LinkedHashMap<String, String> get86finfield(int index, int line) {
		LinkedHashMap<String, String> field86fin = new LinkedHashMap<String, String>();
		String field;
		if ((index >= content.size() || line >= content.get(index).get(":86:").size())
				|| !content.get(index).get(":86:").get(line).contains("SUM")) {
			return null;
		} else if (content.get(index).containsKey(":86:")) {
			field = content.get(index).get(":86:").get(line);
			String[] arrOfStr = field.split("/");
			field86fin.put("Nr. of transactions", arrOfStr[2]);
			field86fin.put("Final amount of the transactions ", arrOfStr[4]);
		}
		return field86fin;
	}

	public void uploadToDatabase(String mt3,String filename) {
		Long dateTime = System.currentTimeMillis();
		int index = 0;
		while (index != -1) {
			int previndex = index;
			String tmp = null;
			if (mt3.contains("-}")) {
				index = mt3.indexOf("-}", previndex + 1);
				if (index != -1) {
					tmp = mt3.substring(previndex, index);
				}
			} else {
				tmp = "{4:" + mt3;
				index = -1;
			}
			if (tmp != null) {
				tmp = tmp.replace(":28:", ":28C:");
				MT940 file = new MT940(tmp);
				FileDAO dao = new FileDAO();
				dao.addFileDetails(file,tmp,dateTime,filename);
			}
		}
	}
}
