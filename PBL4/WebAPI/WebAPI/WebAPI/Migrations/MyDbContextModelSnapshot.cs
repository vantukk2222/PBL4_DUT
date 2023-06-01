﻿// <auto-generated />
using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using WebAPI.Data;

#nullable disable

namespace WebAPI.Migrations
{
    [DbContext(typeof(MyDbContext))]
    partial class MyDbContextModelSnapshot : ModelSnapshot
    {
        protected override void BuildModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("ProductVersion", "6.0.9")
                .HasAnnotation("Relational:MaxIdentifierLength", 64);

            modelBuilder.Entity("WebAPI.Model.Account", b =>
                {
                    b.Property<int?>("ID_Account")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    b.Property<string>("Password")
                        .HasColumnType("longtext");

                    b.Property<int?>("Position")
                        .HasColumnType("int");

                    b.Property<string>("UserName")
                        .HasColumnType("longtext");

                    b.HasKey("ID_Account");

                    b.ToTable("Account");
                });

            modelBuilder.Entity("WebAPI.Model.Cart", b =>
                {
                    b.Property<int>("ID_Cart")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    b.Property<int>("ID_Account")
                        .HasColumnType("int");

                    b.Property<int>("ID_Sach")
                        .HasColumnType("int");

                    b.Property<int>("SoLuong")
                        .HasColumnType("int");

                    b.Property<int>("TongTien")
                        .HasColumnType("int");

                    b.HasKey("ID_Cart");

                    b.HasIndex("ID_Account");

                    b.HasIndex("ID_Sach");

                    b.ToTable("Cart");
                });

            modelBuilder.Entity("WebAPI.Model.ChiTietHoaDon", b =>
                {
                    b.Property<int>("ID_ChiTietHoaDon")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    b.Property<int>("ID_HoaDon")
                        .HasColumnType("int");

                    b.Property<int>("ID_Sach")
                        .HasColumnType("int");

                    b.Property<int>("SoLuong")
                        .HasColumnType("int");

                    b.Property<int>("TongTien")
                        .HasColumnType("int");

                    b.HasKey("ID_ChiTietHoaDon");

                    b.HasIndex("ID_HoaDon");

                    b.HasIndex("ID_Sach");

                    b.ToTable("ChiTietHoaDon");
                });

            modelBuilder.Entity("WebAPI.Model.HoaDon", b =>
                {
                    b.Property<int>("ID_HoaDon")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    b.Property<int>("ID_Account")
                        .HasColumnType("int");

                    b.Property<DateTime>("NgayLap")
                        .HasColumnType("datetime(6)");

                    b.Property<int>("TongTien")
                        .HasColumnType("int");

                    b.HasKey("ID_HoaDon");

                    b.HasIndex("ID_Account");

                    b.ToTable("HoaDon");
                });

            modelBuilder.Entity("WebAPI.Model.Person", b =>
                {
                    b.Property<int>("ID_Account")
                        .HasColumnType("int");

                    b.Property<DateTime?>("DateOfBirth")
                        .HasColumnType("datetime(6)");

                    b.Property<string>("Email")
                        .HasMaxLength(100)
                        .HasColumnType("varchar(100)");

                    b.Property<bool?>("Gender")
                        .HasColumnType("tinyint(1)");

                    b.Property<string>("Name")
                        .HasMaxLength(100)
                        .HasColumnType("varchar(100)");

                    b.Property<string>("PhoneNumber")
                        .HasMaxLength(100)
                        .HasColumnType("varchar(100)");

                    b.HasKey("ID_Account");

                    b.ToTable("Person");
                });

            modelBuilder.Entity("WebAPI.Model.Sach", b =>
                {
                    b.Property<int>("ID_Sach")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("int");

                    b.Property<int>("GiaBan")
                        .HasColumnType("int");

                    b.Property<string>("ImgSach")
                        .HasMaxLength(200)
                        .HasColumnType("varchar(200)");

                    b.Property<string>("NamXuatBan")
                        .HasColumnType("longtext");

                    b.Property<int>("SoLuong")
                        .HasColumnType("int");

                    b.Property<int>("SolanTaiBan")
                        .HasColumnType("int");

                    b.Property<string>("TenSach")
                        .HasMaxLength(100)
                        .HasColumnType("varchar(100)");

                    b.Property<string>("TenTacGia")
                        .HasMaxLength(100)
                        .HasColumnType("varchar(100)");

                    b.Property<string>("Theloai")
                        .HasMaxLength(100)
                        .HasColumnType("varchar(100)");

                    b.HasKey("ID_Sach");

                    b.ToTable("Sach");
                });

            modelBuilder.Entity("WebAPI.Model.Cart", b =>
                {
                    b.HasOne("WebAPI.Model.Account", "Account")
                        .WithMany()
                        .HasForeignKey("ID_Account")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("WebAPI.Model.Sach", "Sach")
                        .WithMany()
                        .HasForeignKey("ID_Sach")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("Account");

                    b.Navigation("Sach");
                });

            modelBuilder.Entity("WebAPI.Model.ChiTietHoaDon", b =>
                {
                    b.HasOne("WebAPI.Model.HoaDon", "HoaDon")
                        .WithMany("ChiTietHoaDons")
                        .HasForeignKey("ID_HoaDon")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.HasOne("WebAPI.Model.Sach", "Sach")
                        .WithMany("ChiTietHoaDons")
                        .HasForeignKey("ID_Sach")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("HoaDon");

                    b.Navigation("Sach");
                });

            modelBuilder.Entity("WebAPI.Model.HoaDon", b =>
                {
                    b.HasOne("WebAPI.Model.Account", "Account")
                        .WithMany("HoaDons")
                        .HasForeignKey("ID_Account")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("Account");
                });

            modelBuilder.Entity("WebAPI.Model.Person", b =>
                {
                    b.HasOne("WebAPI.Model.Account", "Account")
                        .WithOne("Person")
                        .HasForeignKey("WebAPI.Model.Person", "ID_Account")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();

                    b.Navigation("Account");
                });

            modelBuilder.Entity("WebAPI.Model.Account", b =>
                {
                    b.Navigation("HoaDons");

                    b.Navigation("Person");
                });

            modelBuilder.Entity("WebAPI.Model.HoaDon", b =>
                {
                    b.Navigation("ChiTietHoaDons");
                });

            modelBuilder.Entity("WebAPI.Model.Sach", b =>
                {
                    b.Navigation("ChiTietHoaDons");
                });
#pragma warning restore 612, 618
        }
    }
}
