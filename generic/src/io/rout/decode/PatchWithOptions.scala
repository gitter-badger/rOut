package io.rout.generic.decoding

import shapeless.labelled._
import shapeless.{::, HList, HNil}
/**
 * Created by betepahos on 02.12.15.
 */
trait PatchWithOptions[R <: HList] {
  type Out <: HList

  def apply(r: R, o: Out): R
}

object PatchWithOptions {
  type Aux[R <: HList, Out0 <: HList] = PatchWithOptions[R] { type Out = Out0 }

  implicit val hnilPatchWithOptions: Aux[HNil, HNil] =
    new PatchWithOptions[HNil] {
      type Out = HNil

      def apply(r: HNil, o: HNil): HNil = HNil
    }

  implicit def hconsPatchWithOptions[K <: Symbol, V, T <: HList](implicit
   tailPatch: PatchWithOptions[T]
  ): Aux[FieldType[K, V] :: T, FieldType[K, Option[V]] :: tailPatch.Out] =
    new PatchWithOptions[FieldType[K, V] :: T] {
      type Out = FieldType[K, Option[V]] :: tailPatch.Out

      def apply(
                 r: FieldType[K, V] :: T,
                 o: FieldType[K, Option[V]] :: tailPatch.Out
                 ): FieldType[K, V] :: T =
        field[K](o.head.getOrElse(r.head)) :: tailPatch(r.tail, o.tail)
    }
}