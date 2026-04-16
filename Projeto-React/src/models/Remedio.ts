import { Entity, PrimaryGeneratedColumn, Column, OneToOne } from "typeorm";
import { Categoria_Remedio } from "./Categoria_Remedio";

@Entity()
export class Remedio {
  @PrimaryGeneratedColumn()
  remedio_Id!: number;

  @Column({ type: "varchar", length: 125 })
  remedio_Nome!: string;

  @Column({ type: "varchar", length: 75 })
  remedio_Dosagem!: string;

  @Column({ type: "varchar", length: 20 })
  remedio_Formula_Farmaceutica!: string;

  @OneToOne(()=> Categoria_Remedio, (categoria)=> categoria.remedio, {cascade: true})
  Fk_Categoria_Remedio!: Categoria_Remedio
}