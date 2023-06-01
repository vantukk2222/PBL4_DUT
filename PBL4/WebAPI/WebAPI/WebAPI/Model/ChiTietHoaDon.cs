using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace WebAPI.Model
{
    [Table("ChiTietHoaDon")]
    public class ChiTietHoaDon
    {
        [Key]
        [Required]
        public int ID_ChiTietHoaDon { get; set; }
        public int ID_HoaDon { get; set; }
        public int ID_Sach { get; set; }
        public int SoLuong { get; set; }
        public int TongTien { get; set; }

        [ForeignKey("ID_HoaDon")]
        public virtual HoaDon? HoaDon { get; set; }

        [ForeignKey("ID_Sach")]
        public virtual Sach? Sach { get; set; }
    }
}
