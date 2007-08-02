package chessclient.enums;

/* this enum is used to determine which squares are possible move squares */
public enum SquareState {
	GOOD, //this square is a good destination to land on
	BAD, // you can not stand here!
	GOOD_BUT_LAST_ONE // an enemy piece stands here. Its a good square but its blocking
}
