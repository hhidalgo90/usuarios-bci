package com.prueba.usuarios.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @NotBlank(message = "Nombre no puede estar vacio")
    @Size(min = 4, max = 20, message = " El largo debe ser entre 4 y 12 caracteres")
    @Column(length = 20)
    private String name;

    @NotBlank(message = "Password no puede estar vacio")
    @Size(min = 4, max = 60, message = " El largo debe ser entre 4 y 60 caracteres")
    @Column(length = 60)
    private String password;

    @NotBlank(message = "Email no puede estar vacio")
    @Email(message = "Email debe tener un formato valido")
    @Column(nullable = false, unique = true)
    private String email;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usuarios_phones", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "phone_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id", "phone_id"})})
    private Set<Telefono> phones;

    @Column(name = "isActive")
    private Boolean isActive;

    @Column(name = "created")
    private LocalDate created;

    @Column(name = "modified")
    private LocalDate modified;

    @Column(name = "last_login")
    private LocalDate last_login;

    @Column(name = "token", length = 1000)
    private String token;

    @JsonIgnore
    @Transient
    private boolean tieneErrores;

    @JsonIgnore
    @Transient
    Map<String, Object> mensaje;

}
