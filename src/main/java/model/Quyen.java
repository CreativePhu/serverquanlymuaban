package model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "quyen")
public class Quyen implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_quyen")
	private long idQuyen;
	
	@Column(name = "ten_quyen", length = 50, nullable = false, unique = true)
	private String tenQuyen;

	public Quyen(String tenQuyen) {
		this.tenQuyen = tenQuyen;
	}

	@Override
	public String toString() {
		return tenQuyen;
	}

}
