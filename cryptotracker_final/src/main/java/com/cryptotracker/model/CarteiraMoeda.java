@Entity
@Table(name="carteira_moedas")
public class CarteiraMoeda {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Long carteiraId;
    private Long moedaId;
    private double quantidade;
    private boolean favorito;

    public CarteiraMoeda() {}

    public CarteiraMoeda(Long carteiraId, Long moedaId, double quantidade){
        this.carteiraId = carteiraId;
        this.moedaId = moedaId;
        this.quantidade = quantidade;
        this.favorito = false;
    }

    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }

    public Long getCarteiraId(){ return carteiraId; }
    public void setCarteiraId(Long carteiraId){ this.carteiraId = carteiraId; }

    public Long getMoedaId(){ return moedaId; }
    public void setMoedaId(Long moedaId){ this.moedaId = moedaId; }

    public double getQuantidade(){ return quantidade; }
    public void setQuantidade(double quantidade){ this.quantidade = quantidade; }

    public boolean isFavorito(){ return favorito; }
    public void setFavorito(boolean favorito){ this.favorito = favorito; }
}
