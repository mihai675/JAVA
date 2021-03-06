package exercise18;

public class Ex141 extends Player{

	   public Ex141(String name){
	      super(name);
	   }
	   
		public Card play(Eights eights, Card prev) {
			Card card = searchForMatch(prev);
			if (card == null) {
				card = drawForMatch(eights, prev);
				}
			return card;
		}
		
		public Card searchForMatch(Card prev) {
			for (int i = 0; i < super.getHand().size(); i++) {
				Card card = super.getHand().getCard(i);
				if (card.getRank() == 8)
					return super.getHand().popCard(i);
				else if (cardMatches(card, prev)) {
					return super.getHand().popCard(i);
				}
			}
			return null;
		}
}
