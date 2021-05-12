package DP_OBSERVATEUR;

public interface Observable {
    void attacheObservateur(Observateur o);
    void detacheObservateur(Observateur o);
    void notifieObservateurs();
}
