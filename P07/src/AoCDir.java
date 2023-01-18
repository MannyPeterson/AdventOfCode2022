
public class AoCDir {

	

		private String name;
		private String path;
		private int size;

		public AoCDir(String path, String name, int size) {
			this.path = path;
			this.name = name;
			this.size = size;
		}

		public String getName() {
			return name;
		}

		public String getPath() {
			return path;
		}

		public int getSize() {
			return size;
		}

		public void addFileSize(int size) {
			this.size += size;
		}
		
		public String toString() {
			StringBuilder ret = new StringBuilder();
			
			ret.append("Dir ");
			ret.append(this.name);
			ret.append(" path is ");
			ret.append(this.path);
			ret.append(" and contains ");
			ret.append(Integer.toString(this.size));
			ret.append(" bytes in files...");
			
			return ret.toString();
		}
}
