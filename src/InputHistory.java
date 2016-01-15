/**
 * InputHistory est une classe pour la GUI
 * C'est une implémentation d'un historique des
 * termes rentrés dans le <code>JTextField</code> servant
 * à interagir avec le programmer.
 * L'historique permet de naviaguer entre les termes précédemment entrés,
 * comme dans un Terminal.
 */
public class InputHistory {

    String[] inputArray;
    int maxLength;
    int length = 0;
    int position = -1;
    int mostRecent = -1;

    /**
     * La taille maximale est fixée à 100 termes.
     */
    public InputHistory() {
        this.maxLength = 100;
        this.inputArray = new String[maxLength];
    }

    /**
     * Ajoute un terme à l'historique, et si la taille de l'historique est supérieure
     * à la taille maximale, il y a un shift entre le plus ancien terme et le nouveau
     *
     * @param input Le terme à ajouter
     */
    public void addInput(String input) {
        if (this.length >= maxLength) {
            this.mostRecent = (this.mostRecent + 1) % this.maxLength;
            this.position = this.mostRecent;
            this.inputArray[this.position] = input;
        } else {
            this.length++;
            this.mostRecent = this.length - 1;
            this.position = this.mostRecent;
            this.inputArray[this.position] = input;
        }
    }


    /**
     * Cette méthode permet de naviguer dans l'historique
     * du plus ancien terme au plus récent.
     *
     * @return Le terme correspondant
     */
    public String getNextInput() {

        if (this.length == 0 || this.position >= this.length - 1) {
            return "";
        }

        this.position = (this.position + 1);
        return this.inputArray[this.position];

    }


    /**
     * Cette méthode permet de naviguer dans l'historique
     * du plus récent terme au plus ancien.
     *
     * @return Le terme correspondant
     */
    public String getPrevInput() {

        if (this.length == 0) {
            return "";
        }

        String returnString = this.inputArray[this.position];
        int oldPos = this.position--;
        if (this.position < 0) {
            this.position += this.length;
        }

        if (this.position == this.mostRecent) {
            this.position = oldPos;
        }

        return returnString;

    }
}
