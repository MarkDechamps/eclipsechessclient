package chessclient.enums;

public enum Pieces {
	/* all pieces except king and pawn can be promoted into */
	Bishop, Rook, Queen,Knight, 
	King {
		public boolean canBePromotion() {
			return false;
		}
	},
	Pawn {
		public boolean canBePromotion() {
			return false;
		}
	};

	public boolean canBePromotion() {
		/* can this piece be used as promotion piece */
		return true;
	}
}
