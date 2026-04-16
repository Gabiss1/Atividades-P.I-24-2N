import { Entity, PrimaryGeneratedColumn, Column, OneToOne } from "typeorm";
import { Tipo_Usuario } from "./Tipo_Usuario";

@Entity()
export class Usuario {
  @PrimaryGeneratedColumn()
  usuario_Id!: number;
  
  @Column()
  usuario_Nome!: string;

  @Column({ unique: true })
  usuario_Email!: string;

  @Column({ unique: true })
  usuario_Endereco!: string;

  @Column({ unique: true })
  usuario_Contato!: string;
  
  @Column({ unique: true })
  usuario_Data_Nascimento!: string;

  @OneToOne(()=> Tipo_Usuario, (tipo_usuario)=> tipo_usuario.usuario, {cascade: true})
  Fk_tipo_usuario!: Tipo_Usuario
}