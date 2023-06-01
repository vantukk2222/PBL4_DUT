using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace WebAPI.Model
{
    [Table("Sach")]
    public class Sach
    {
        public Sach()
        {
            this.ChiTietHoaDons = new HashSet<ChiTietHoaDon>();
        }

        [Key]
        [Required]
        public int ID_Sach { get; set; }

        [MaxLength(100)]
        public string? TenSach { get; set; }

        [MaxLength(100)]
        public string? Theloai { get; set; }

        [MaxLength(200)]
        public string? ImgSach { get; set; }

        [MaxLength(100)]
        public string? TenTacGia { get; set; }
        public int SolanTaiBan { get; set; }
        public string? NamXuatBan { get; set; }
        public int GiaBan { get; set; }
        public int SoLuong { get; set; }
        public virtual ICollection<ChiTietHoaDon>? ChiTietHoaDons { get; set; }
    
    }
}
