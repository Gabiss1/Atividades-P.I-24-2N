import { Entity, PrimaryGeneratedColumn, Column, OneToOne } from "typeorm";
import { Usuario } from "./Usuario";

@Entity()
export class Tipo_Usuario {
  @PrimaryGeneratedColumn()
  tipo_usuario_Id!: number;

  @Column()
  tipo_usuario_Nome!: string;

  @OneToOne(() => Usuario, (usuario) => usuario.Fk_tipo_usuario, {
    nullable: false,
    onDelete: "CASCADE",
  })
  usuario!: Usuario;
}